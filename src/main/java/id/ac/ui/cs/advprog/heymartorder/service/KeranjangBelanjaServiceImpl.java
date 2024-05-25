package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.dto.GetProductResponse;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangItemRepository;
import id.ac.ui.cs.advprog.heymartorder.rest.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService{

    @Autowired
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Autowired
    private KeranjangItemRepository keranjangItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private KeranjangLogService logService;

    @Override
    public KeranjangBelanja createKeranjangBelanja(Long userId) {
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder()
                                            .setId(userId)
                                            .setSupermarketId(null)
                                            .build();
        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public KeranjangBelanja findKeranjangById(Long userId) {
        return keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
    }

    @Override
    public KeranjangBelanja clearKeranjang(Long userId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();

        for (KeranjangItem item : items) {
            keranjangItemRepository.delete(item);
        }

        items.clear();
        keranjangBelanja.setSupermarketId(null);

        CompletableFuture<Void> logFuture = logService.logCartCleared(userId);
        logFuture.join();

        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public synchronized KeranjangBelanja addProductToKeranjang(Long userId, String productId, Long supermarketId, String token) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();

        if (keranjangBelanja.getSupermarketId() == null) {
            keranjangBelanja.setSupermarketId(supermarketId);
        } else if (!keranjangBelanja.getSupermarketId().equals(supermarketId)) {
            throw new IllegalArgumentException("Supermarket ID mismatch");
        }

        GetProductResponse productResponse = productService.getProductById(productId, token);
        if (productResponse == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Integer stock = productResponse.getStock();
        if (stock == 0) {
            throw new IllegalArgumentException("Product is out of stock");
        }

        boolean productExists = false;
        for (KeranjangItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getAmount() + 1 <= stock) {
                    item.setAmount(item.getAmount() + 1);
                    productExists = true;
                } else {
                    throw new IllegalArgumentException("Insufficient stock");
                }
                break;
            }
        }

        if (!productExists) {
            KeranjangItem newItem = KeranjangItem.getBuilder()
                    .setProductId(productId)
                    .setAmount(1)
                    .build();
            newItem.setKeranjangbelanja(keranjangBelanja);
            items.add(newItem);
        }

        CompletableFuture<Void> logFuture = logService.logProductAddedToCart(userId, productId);
        logFuture.join();

        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public KeranjangBelanja removeProductFromKeranjang(Long userId, String productId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();

        KeranjangItem itemToRemove = null;
        for (KeranjangItem item : items) {
            if (item.getProductId().equals(productId)) {
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                } else {
                    itemToRemove = item;
                }
                break;
            }
        }

        if (itemToRemove != null) {
            KeranjangItem keranjangItem = keranjangItemRepository.findByProductId(itemToRemove.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            keranjangItemRepository.delete(keranjangItem);
            items.remove(itemToRemove);
        }

        if (items.isEmpty()) {
            keranjangBelanja.setSupermarketId(null);
        }

        CompletableFuture<Void> logFuture = logService.logProductRemovedFromCart(userId, productId);
        logFuture.join();

        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public Long countTotal(Long userId, String token) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        Long supermarketId = keranjangBelanja.getSupermarketId();
        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();

        List<GetProductResponse> products = productService.getAllProduct(supermarketId, token);
        Map<String, Long> productPriceMap = products.stream()
                .collect(Collectors.toMap(GetProductResponse::getUUID, GetProductResponse::getPrice));

        long total = 0L;
        for (KeranjangItem item : items) {
            Long price = productPriceMap.get(item.getProductId());
            if (price != null) {
                total += price * item.getAmount();
            }
        }

        return total;
    }
}

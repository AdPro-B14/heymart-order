package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService{

    @Autowired
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Autowired
    private KeranjangItemRepository keranjangItemRepository;

    @Override
    public KeranjangBelanja createKeranjangBelanja(Long userId) {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
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

        return keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public KeranjangBelanja addProductToKeranjang(Long userId, String productId, Long supermarketId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();

        if (keranjangBelanja.getSupermarketId() == null) {
            keranjangBelanja.setSupermarketId(supermarketId);
        } else if (!keranjangBelanja.getSupermarketId().equals(supermarketId)) {
            throw new IllegalArgumentException("Supermarket ID mismatch");
        }

        boolean productExists = false;
        for (KeranjangItem item : items) {
            if (item.getProductId().equals(productId)) {
                item.setAmount(item.getAmount() + 1);
                productExists = true;
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

        return keranjangBelanjaRepository.save(keranjangBelanja);
    }


    @Override
    public Integer countTotal(HashMap<String, Integer> productMap) {
        // todo perlu harga dari produk
//        Long total = 0;
        /** For string in productMap:
         * Manggil findById untuk tiap string
         * total += Product.getharga * Integer dari productMapnya
         */
        return null;
    }

    @Override
    public boolean checkout() {
        // todo kurangin stok
        return false;
    }
}

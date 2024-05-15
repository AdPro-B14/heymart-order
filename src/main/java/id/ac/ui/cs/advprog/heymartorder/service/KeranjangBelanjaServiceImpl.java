package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService{

    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Override
    public KeranjangBelanja createKeranjangBelanja(Long userId) {
        return new KeranjangBelanjaBuilder()
            .setId(userId)
            .setSupermarketId(null)
            .setProductMap(new HashMap<String, Integer>())
            .build();
    }

    @Override
    public KeranjangBelanja findKeranjangById(Long userId) {
        return keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
    }

    @Override
    public void clearKeranjang(Long userId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        keranjangBelanja.setSupermarketId(null);
        keranjangBelanja.getProductMap().clear();
        keranjangBelanjaRepository.save(keranjangBelanja);
    }

    @Override
    public KeranjangBelanja addProductToKeranjang(Long userId, String productId, Long supermarketId) {
        KeranjangBelanja keranjangBelanja = keranjangBelanjaRepository.findKeranjangBelanjaById(userId).orElseThrow();
        HashMap<String, Integer> productMap = keranjangBelanja.getProductMap();
        if (keranjangBelanja.getSupermarketId() == null) {
            keranjangBelanja.setSupermarketId(supermarketId);
        } else {
            if (keranjangBelanja.getSupermarketId().equals(supermarketId)) {
                if (productMap.containsKey(productId)) {
                    productMap.put(productId, productMap.get(productId) + 1);
                } else {
                    productMap.put(productId, 1);
                }
            }
            else {
                return null;
                // todo tambahin error
            }
        }
        return keranjangBelanja;
    }

    @Override
    public Integer countTotal(HashMap<String, Integer> productMap) {
        // todo perlu harga dari produk
        return null;
    }

    @Override
    public boolean checkout() {
        // todo kurangin stok
        return false;
    }
}

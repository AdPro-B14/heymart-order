package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;

import java.util.HashMap;

public interface KeranjangBelanjaService {
    KeranjangBelanja createKeranjangBelanja(Long userId);
    KeranjangBelanja findKeranjangById(Long userId);
    void clearKeranjang(Long userId);
    KeranjangBelanja addProductToKeranjang(Long userId, String productId, Long supermarketId);
    Integer countTotal(HashMap<String, Integer> productMap);
    boolean checkout();
}

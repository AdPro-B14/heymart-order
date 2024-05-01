package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;

public interface KeranjangBelanjaService {
    KeranjangBelanja findKeranjangBelanjaById(Long id);
    void clearKeranjang();
    KeranjangBelanja addProductToKeranjang(String productId);
}

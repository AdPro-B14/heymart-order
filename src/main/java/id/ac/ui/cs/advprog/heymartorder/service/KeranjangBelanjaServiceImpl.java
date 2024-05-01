package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import org.springframework.stereotype.Service;

@Service
public class KeranjangBelanjaServiceImpl implements KeranjangBelanjaService{

    @Override
    public KeranjangBelanja findKeranjangBelanjaById(Long id) {
        return null;
    }

    @Override
    public void clearKeranjang() {

    }

    @Override
    public KeranjangBelanja addProductToKeranjang(String productId) {
        return null;
    }
}

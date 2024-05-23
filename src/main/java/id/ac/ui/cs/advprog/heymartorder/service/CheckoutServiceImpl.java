package id.ac.ui.cs.advprog.heymartorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Override
    public boolean checkout(Long userId) {
        try {
            Long total = keranjangBelanjaService.countTotal(userId);

            /*
            TODO
             */

            keranjangBelanjaService.clearKeranjang(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

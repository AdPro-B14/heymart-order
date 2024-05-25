package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Override
    public boolean checkout(Long userId, String token) {
        try {
            Long total = keranjangBelanjaService.countTotal(userId, token);

            /*
            TODO
             */

            keranjangBelanjaService.clearKeranjang(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long checkoutWithCoupon(Long userId, String token, ProductCoupon productCoupon, TransactionCoupon transactionCoupon) {
        try {
            Long total = keranjangBelanjaService.countTotal(userId, token);

            if (productCoupon != null) {
                total = applyProductCoupon(userId, token, productCoupon);
            }
            if (transactionCoupon != null) {
                total = applyTransactionCoupon(userId, token, transactionCoupon);
            }

            keranjangBelanjaService.clearKeranjang(userId);
            return total;
        } catch (Exception e) {
            return 0L;
        }
    }

    public Long applyProductCoupon(Long userId, String token, ProductCoupon productCoupon) {
        try {
            List<KeranjangItem> items = keranjangBelanjaService.findKeranjangById(userId).getListKeranjangItem();
            Long currentTotal = keranjangBelanjaService.countTotal(userId, token);
            for (KeranjangItem i : items) {
                if (i.getProductId().equals(productCoupon.getProductId())) {
                    long couponSum = productCoupon.getCouponNominal() * i.getAmount();
                    currentTotal -= couponSum;
                }
            }
            return currentTotal;
        } catch (Exception e) {
            return 0L;
        }
    }

    public Long applyTransactionCoupon(Long userId, String token, TransactionCoupon transactionCoupon) {
        try {
            Long currentTotal = keranjangBelanjaService.countTotal(userId, token);
            if (currentTotal >= transactionCoupon.getMinimumBuy()) {
                return currentTotal - transactionCoupon.getCouponNominal();
            }
            else {
                throw new IllegalArgumentException("Cart total is not enough to use coupon");
            }
        } catch (Exception e) {
            return 0L;
        }
    }

}

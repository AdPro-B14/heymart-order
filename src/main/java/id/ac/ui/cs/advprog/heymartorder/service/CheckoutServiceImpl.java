package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Autowired
    private TransactionCouponServiceImpl transactionCouponService;

    @Autowired
    private ProductCouponServiceImpl productCouponService;

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
    public Long checkoutWithCoupon(Long userId, String token, List<String> productCouponIds, String transactionCouponId) {
        try {
            Long total = keranjangBelanjaService.countTotal(userId, token);

            if (productCouponIds != null) {
                total = applyProductCoupon(userId, token, productCouponIds);
            }
            if (transactionCouponId != null) {
                total = applyTransactionCoupon(userId, token, transactionCouponId);
            }

            keranjangBelanjaService.clearKeranjang(userId);
            return total;
        } catch (Exception e) {
            return 0L;
        }
    }

    public Long applyProductCoupon(Long userId, String token, List<String> productCouponIds) {
        try {
            Long couponSum = 0L;
            List<KeranjangItem> items = keranjangBelanjaService.findKeranjangById(userId).getListKeranjangItem();
            Long currentTotal = keranjangBelanjaService.countTotal(userId, token);

            Map<String, ProductCoupon> productCouponMap = new HashMap<>();
            for (String couponId : productCouponIds) {
                ProductCoupon productCoupon = productCouponService.findById(couponId);
                productCouponMap.put(productCoupon.getProductId(), productCoupon);
            }

            for (KeranjangItem item : items) {
                ProductCoupon productCoupon = productCouponMap.get(item.getProductId());
                if (productCoupon != null) {
                    couponSum += productCoupon.getCouponNominal()*item.getAmount();
                }
            }

            currentTotal -= couponSum;
            return currentTotal;

        } catch (Exception e) {
            return 0L;
        }
    }

    public Long applyTransactionCoupon(Long userId, String token, String transactionCouponId) {
        try {
            Long currentTotal = keranjangBelanjaService.countTotal(userId, token);
            TransactionCoupon transactionCoupon = transactionCouponService.findById(transactionCouponId);
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

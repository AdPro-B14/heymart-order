package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public interface CheckoutService {
    Long checkoutWithCoupon(Long userId, String token, List<String> productCouponId, String transactionCouponId);
    public Long applyProductCoupon(Long userId, String token, List<String> productCouponIds);
    public Long applyTransactionCoupon(Long userId, String token, String transactionCouponId);
    public void processCheckoutBalance(KeranjangBelanja keranjangBelanja, Long total);
}

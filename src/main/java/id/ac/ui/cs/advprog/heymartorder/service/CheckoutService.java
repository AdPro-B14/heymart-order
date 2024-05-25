package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public interface CheckoutService {
    boolean checkout(Long userId, String token);
    Long checkoutWithCoupon(Long userId, String token, List<String> productCouponId, String transactionCouponId);
}

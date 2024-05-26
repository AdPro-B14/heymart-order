package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public class TransactionCouponFactory {

    public TransactionCoupon orderCoupon(Long supermarketId, String couponName, Long couponNominal, Long minimumBuy) {
        return createCoupon(supermarketId, couponName, couponNominal, minimumBuy);
    }
    public TransactionCoupon createCoupon(Long supermarketId, String couponName, Long couponNominal, Long minimumBuy) {
        if (couponNominal <= 0) {
            throw new IllegalArgumentException("Coupon nominal must be greater than 0");
        }
        if (minimumBuy <= 0) {
            throw new IllegalArgumentException("Minimum buy must be greater than 0");
        }
        if (minimumBuy <= couponNominal) {
            throw new IllegalArgumentException("Minimum buy must be greater than couponNominal");
        }
        if (couponName == null) {
            throw new IllegalArgumentException("Coupon name cant be null");
        }
        return new TransactionCoupon(supermarketId, couponName, couponNominal, minimumBuy);
    }
}

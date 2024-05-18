package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

public class TransactionCouponFactory {

    public TransactionCoupon orderCoupon(String couponId, Long supermarketId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        TransactionCoupon coupon = createCoupon(couponId, supermarketId, couponName, couponNominal, isUsed, minimumBuy);
        coupon.prepare();
        return coupon;
    }
    public TransactionCoupon createCoupon(String couponId, Long supermarketId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        return new TransactionCoupon(couponId, supermarketId, couponName, couponNominal, isUsed, minimumBuy);
    }
}

package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public class TransactionCouponFactory {

    public TransactionCoupon orderCoupon(Long supermarketId, String couponName, Long couponNominal, Long minimumBuy) {
        TransactionCoupon coupon = createCoupon(supermarketId, couponName, couponNominal, minimumBuy);
        coupon.prepare();
        return coupon;
    }
    public TransactionCoupon createCoupon(Long supermarketId, String couponName, Long couponNominal, Long minimumBuy) {
        return new TransactionCoupon(supermarketId, couponName, couponNominal, minimumBuy);
    }
}

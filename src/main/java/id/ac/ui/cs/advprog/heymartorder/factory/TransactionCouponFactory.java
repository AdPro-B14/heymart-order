package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.Coupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

public class TransactionCouponFactory {

    public Coupon orderCoupon(String couponId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        Coupon coupon = createCoupon(couponId, couponName, couponNominal, isUsed, minimumBuy);
        coupon.prepare();
        return coupon;
    }
    public Coupon createCoupon(String couponId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        return new TransactionCoupon(couponId, couponName, couponNominal, isUsed, minimumBuy);
    }
}

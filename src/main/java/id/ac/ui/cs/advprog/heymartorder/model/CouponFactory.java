package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.model.Coupon;

public abstract class CouponFactory {
    public Coupon orderCoupon() {
        Coupon coupon = createCoupon();
        coupon.prepare();
        return coupon;
    }
    abstract Coupon createCoupon();
}

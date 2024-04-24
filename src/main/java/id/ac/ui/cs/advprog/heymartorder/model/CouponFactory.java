package id.ac.ui.cs.advprog.heymartorder.model;

abstract class CouponFactory {

    public Coupon orderCoupon() {
        Coupon coupon = createCoupon();
        coupon.prepare();
        return coupon;
    }

    public abstract Coupon createCoupon();

}

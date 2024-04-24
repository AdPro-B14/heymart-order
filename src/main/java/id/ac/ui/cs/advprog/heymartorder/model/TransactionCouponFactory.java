package id.ac.ui.cs.advprog.heymartorder.model;

public class TransactionCouponFactory extends CouponFactory {
    public Coupon createCoupon() {
        return new TransactionCoupon();
    }
}

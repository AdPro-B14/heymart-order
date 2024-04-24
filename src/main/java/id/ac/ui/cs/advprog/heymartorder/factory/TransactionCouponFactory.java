package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.factory.CouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.Coupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

public class TransactionCouponFactory extends CouponFactory {
    public Coupon createCoupon() {
        return new TransactionCoupon();
    }
}

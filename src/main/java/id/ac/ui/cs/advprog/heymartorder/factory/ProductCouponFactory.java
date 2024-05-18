package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

public class ProductCouponFactory {

    public ProductCoupon orderCoupon(String couponId, Long supermarketId, String productId, String couponName, Long couponNominal) {
        ProductCoupon coupon = createCoupon(couponId, supermarketId, productId, couponName, couponNominal);
        coupon.prepare();
        return coupon;
    }
    public ProductCoupon createCoupon(String couponId, Long supermarketId, String productId, String couponName, Long couponNominal) {
        return new ProductCoupon(couponId, supermarketId, productId, couponName, couponNominal);
    }

}

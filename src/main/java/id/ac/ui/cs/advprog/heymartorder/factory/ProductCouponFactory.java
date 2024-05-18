package id.ac.ui.cs.advprog.heymartorder.factory;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

public class ProductCouponFactory {

    public ProductCoupon orderCoupon(Long supermarketId, String couponName, Long couponNominal, String productId) {
        ProductCoupon coupon = createCoupon(supermarketId, couponName, couponNominal, productId);
        coupon.prepare();
        return coupon;
    }
    public ProductCoupon createCoupon(Long supermarketId, String couponName, Long couponNominal, String productId) {
        return new ProductCoupon(supermarketId, couponName, couponNominal, productId);
    }

}

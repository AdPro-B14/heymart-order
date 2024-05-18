package id.ac.ui.cs.advprog.heymartorder.model;

public class ProductCoupon extends Coupon {
    public ProductCoupon(String couponId, Long supermarketId, String productId, String couponName, Long couponNominal) {
        super(couponId, supermarketId, couponName, couponNominal);
        this.productId = productId;
    }

    protected ProductCoupon() {
        super();
    }

    @Override
    public void prepare() {
        // Implementation of prepare method
    }
}

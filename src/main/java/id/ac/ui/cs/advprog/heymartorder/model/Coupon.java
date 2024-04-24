package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Coupon {
    String couponId;
    String couponName;
    long couponNominal;
    long minimumBuy;
    boolean isUsed;
    String productId;

    Coupon(String couponId, String couponName, long couponNominal) {
        this.couponId = couponId;
        this.couponName = couponName;
        this.couponNominal = couponNominal;
    }

    public abstract void prepare();

}

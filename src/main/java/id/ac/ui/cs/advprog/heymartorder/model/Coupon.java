package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Coupon {
    String couponId;
    String couponName;
    Long couponNominal;
    public abstract void prepare();

}

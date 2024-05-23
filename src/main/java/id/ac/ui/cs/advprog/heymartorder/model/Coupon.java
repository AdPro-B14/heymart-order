package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    Coupon() {
        // Default constructor
    }

    public abstract void prepare();
}
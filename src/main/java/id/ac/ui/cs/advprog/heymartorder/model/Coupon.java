package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@Entity
public abstract class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    public Coupon() {

    }

    public abstract void prepare();
}
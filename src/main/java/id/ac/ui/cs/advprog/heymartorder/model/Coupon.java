package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String couponId;

    Long supermarketId;

    String couponName;
    long couponNominal;


    // supermarketId
    Coupon(Long supermarketId, String couponName, long couponNominal) {
        this.supermarketId = supermarketId;
        this.couponName = couponName;
        this.couponNominal = couponNominal;
    }

    public Coupon() {

    }

}
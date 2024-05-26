package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "product_coupon")
@Getter
@Setter
@Entity
public class ProductCoupon extends Coupon {


    String productId;

    public ProductCoupon(Long supermarketId, String couponName, Long couponNominal, String productId) {
        super(supermarketId, couponName, couponNominal);
        this.productId = productId;
    }

    protected ProductCoupon() {
        super();
    }

}

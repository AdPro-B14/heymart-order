package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "transaction_coupon")
@Getter
@Setter
@Entity
public class TransactionCoupon extends Coupon {

    long minimumBuy;

    public TransactionCoupon(Long supermarketId, String couponName, Long couponNominal, Long minimumBuy) {
        super(supermarketId, couponName, couponNominal);
        this.minimumBuy = minimumBuy;
    }

    protected TransactionCoupon() {
        super();
    }

    @Override
    public void prepare() {
        // Implementation of prepare method
    }
}

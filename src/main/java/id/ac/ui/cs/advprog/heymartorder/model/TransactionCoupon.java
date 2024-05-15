package id.ac.ui.cs.advprog.heymartorder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Table(name = "transaction_coupon")
@Getter
@Setter
@Entity
public class TransactionCoupon extends Coupon {

    public TransactionCoupon(String couponId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        super(couponId, couponName, couponNominal);
        this.isUsed = isUsed;
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

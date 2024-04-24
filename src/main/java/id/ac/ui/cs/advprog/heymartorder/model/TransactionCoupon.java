package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCoupon extends Coupon{
    public TransactionCoupon(String couponId, String couponName, Long couponNominal, boolean isUsed, Long minimumBuy) {
        super(couponId, couponName, couponNominal);
        this.isUsed = isUsed;
        this.minimumBuy = minimumBuy;
    }

    public void prepare() {

    }
}

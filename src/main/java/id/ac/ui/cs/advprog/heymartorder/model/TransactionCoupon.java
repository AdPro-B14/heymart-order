package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCoupon extends Coupon{

    long minimumBuy;

    @Getter
    boolean used;


    public void prepare() {

    }
}

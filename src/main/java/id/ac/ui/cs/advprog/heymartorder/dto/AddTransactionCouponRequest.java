package id.ac.ui.cs.advprog.heymartorder.dto;

public class AddTransactionCouponRequest {
    public String couponId;
    String couponName;
    long couponNominal;
    long minimumBuy;
    boolean isUsed;
    String productId;
}

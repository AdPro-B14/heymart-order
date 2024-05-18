package id.ac.ui.cs.advprog.heymartorder.dto;

public class AddTransactionCouponRequest {
    public String couponId;
    public Long supermarketId;
    public String couponName;
    public long couponNominal;
    public long minimumBuy;
    public boolean isUsed;
}

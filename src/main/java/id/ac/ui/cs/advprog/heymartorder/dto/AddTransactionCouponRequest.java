package id.ac.ui.cs.advprog.heymartorder.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class AddTransactionCouponRequest {
    public Long supermarketId;
    public String couponName;
    public long couponNominal;
    public long minimumBuy;
}

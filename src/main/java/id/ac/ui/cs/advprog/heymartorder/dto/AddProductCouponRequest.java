package id.ac.ui.cs.advprog.heymartorder.dto;

import lombok.Builder;

@Builder
public class AddProductCouponRequest {
    public Long supermarketId;
    public String couponName;
    public long couponNominal;
    public String productId;
}

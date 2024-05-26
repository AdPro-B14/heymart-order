package id.ac.ui.cs.advprog.heymartorder.dto;

import java.util.List;

public class CheckoutRequest {
    private List<String> productCouponIds;
    private String transactionCouponId;

    public List<String> getProductCouponIds() {
        return productCouponIds;
    }

    public void setProductCouponIds(List<String> productCouponIds) {
        this.productCouponIds = productCouponIds;
    }

    public String getTransactionCouponId() {
        return transactionCouponId;
    }

    public void setTransactionCouponId(String transactionCouponId) {
        this.transactionCouponId = transactionCouponId;
    }
}
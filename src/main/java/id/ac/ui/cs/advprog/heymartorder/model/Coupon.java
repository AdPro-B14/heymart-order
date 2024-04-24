package id.ac.ui.cs.advprog.heymartorder.model;

import lombok.Getter;
import lombok.Setter;


public interface Coupon {
    void prepare();

    public void setCouponId(String id);
    public void setName(String name);
    public void setNominal(Long nominal);
}

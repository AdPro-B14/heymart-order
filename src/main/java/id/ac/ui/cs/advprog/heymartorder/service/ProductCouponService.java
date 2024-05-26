package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public interface ProductCouponService {
    public ProductCoupon createProductCoupon(ProductCoupon tcCoupon);
    public ProductCoupon findById(String couponId);
    public List<ProductCoupon> findBySupermarketId(Long supermarketId);
    public List<ProductCoupon> findByProductId(String productId);
    public List<ProductCoupon> findAll();
    public void delete(String id);
}

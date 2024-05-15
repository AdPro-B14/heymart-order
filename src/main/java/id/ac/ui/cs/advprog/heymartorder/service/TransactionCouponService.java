package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;

import java.util.List;

public interface TransactionCouponService {
    public TransactionCoupon createTransactionCoupon(TransactionCoupon tcCoupon);
    public TransactionCoupon updateIsUsed(String couponId, boolean isUsed);
    public TransactionCoupon findById(String couponId);
    public List<TransactionCoupon> findAll();
    public void delete(String id);

}

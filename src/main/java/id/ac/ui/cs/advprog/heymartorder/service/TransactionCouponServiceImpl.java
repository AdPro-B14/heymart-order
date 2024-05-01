package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionCouponServiceImpl implements TransactionCouponService {
    @Autowired
    TransactionCouponRepository transactionCouponRepository;
    @Override
    public TransactionCoupon createTransactionCoupon(TransactionCoupon tcCoupon) {return null;}
    @Override
    public TransactionCoupon updateIsUsed(String couponId, boolean isUsed) {return null;}
    @Override
    public TransactionCoupon findById(String couponId) {return null;}
}

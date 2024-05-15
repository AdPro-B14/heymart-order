package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TransactionCouponServiceImpl implements TransactionCouponService {
    @Autowired
    TransactionCouponRepository transactionCouponRepository;
    @Override
    public TransactionCoupon createTransactionCoupon(TransactionCoupon tcCoupon) {
        if (transactionCouponRepository.findById(tcCoupon.getCouponId()).isEmpty()) {
            transactionCouponRepository.save(tcCoupon);
            return tcCoupon;
        }
        return null;
    }
    @Override
    public TransactionCoupon updateIsUsed(String couponId, boolean isUsed) {
        TransactionCoupon tcCoupon = transactionCouponRepository.findTransactionCouponByCouponId(couponId);
        if (tcCoupon != null) {
            tcCoupon.setUsed(isUsed); // Update the existing coupon
            transactionCouponRepository.save(tcCoupon); // Save the updated coupon
            return tcCoupon;
        } else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public TransactionCoupon findById(String couponId) {
        return transactionCouponRepository.findTransactionCouponByCouponId(couponId);
    }

    @Override
    public List<TransactionCoupon> findAll() {
        return transactionCouponRepository.findAll();
    }
}

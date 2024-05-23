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
        if (tcCoupon == null) {
            throw new IllegalArgumentException();
        } else {
            transactionCouponRepository.save(tcCoupon);
            return tcCoupon;
        }
    }


    @Override
    public TransactionCoupon findById(String couponId) {
        return transactionCouponRepository.findByCouponId(couponId);
    }

    @Override
    public List<TransactionCoupon> findBySupermarketId(Long supermarketId) {
        return transactionCouponRepository.findBySupermarketId(supermarketId);
    }

    @Override
    public List<TransactionCoupon> findAll() {
        return transactionCouponRepository.findAll();
    }

    @Override
    public void delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        } else {
            TransactionCoupon tcCoupon = findById(id);
            transactionCouponRepository.delete(tcCoupon);
        }
    }

}

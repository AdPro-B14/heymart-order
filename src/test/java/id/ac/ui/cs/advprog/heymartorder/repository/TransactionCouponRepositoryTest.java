package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionCouponRepositoryTest {
    TransactionCouponRepository transactionCouponRepository;
    List<TransactionCoupon> tcCoupons;

    @BeforeEach
    void setUp() {
        transactionCouponRepository =  new TransactionCouponRepository();
        this.tcCoupons = new ArrayList<>();

        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();

        TransactionCoupon transactionCoupon1 = transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd6", "Kupon 4.4.24",
                        10000L, false, 50000L);

        TransactionCoupon transactionCoupon2 = transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd7", "Kupon Ramadhan Sale",
                        50000L, false, 100000L);

        tcCoupons.add(transactionCoupon1);
        tcCoupons.add(transactionCoupon2);

    }

    @Test
    void testSaveCreate() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        TransactionCoupon result = transactionCouponRepository.save(tcCoupon);

        TransactionCoupon findResult = transactionCouponRepository.findById(tcCoupons.getFirst().getCouponId());
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
        assertEquals(tcCoupon.getCouponId(), findResult.getCouponId());
        assertEquals(tcCoupon.getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupon.getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupon.getMinimumBuy(), findResult.getMinimumBuy());
        assertEquals(tcCoupon.isUsed(), findResult.isUsed());
    }

    @Test
    void testFindIdIfIdFound() {
        for (TransactionCoupon tcCoupon : tcCoupons) {
            transactionCouponRepository.save(tcCoupon);
        }

        TransactionCoupon findResult = transactionCouponRepository.findById(tcCoupons.getFirst().getCouponId());
        assertEquals(tcCoupons.get(1).getCouponId(), findResult.getCouponId());
        assertEquals(tcCoupons.get(1).getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupons.get(1).getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupons.get(1).getMinimumBuy(), findResult.getMinimumBuy());
        assertEquals(tcCoupons.get(1).isUsed(), findResult.isUsed());
    }

    @Test
    void testFindIdIfIdNotFound() {
        for (TransactionCoupon tcCoupon : tcCoupons) {
            transactionCouponRepository.save(tcCoupon);
        }

        TransactionCoupon findResult = transactionCouponRepository.findById("zczc");
        assertNull(findResult);
    }

}
package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TransactionCouponTest {

    private List<TransactionCoupon> tcCoupons;
    @BeforeEach
    void setUp() {
        this.tcCoupons = new ArrayList<>();

        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();

        TransactionCoupon transactionCoupon1 = (TransactionCoupon) transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd6", "Kupon 4.4.24",
                        10000L, false, 50000L);

        TransactionCoupon transactionCoupon2 = (TransactionCoupon) transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd7", "Kupon Ramadhan Sale",
                        50000L, false, 100000L);


        tcCoupons.add(transactionCoupon1);
        tcCoupons.add(transactionCoupon2);

    }

    @Test
    void testGetCouponId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", tcCoupons.getFirst().getCouponId());
    }

    @Test
    void testGetCouponName() {
        assertEquals("Kupon 4.4.24", tcCoupons.getFirst().getCouponName());
    }

    @Test
    void testGetCouponNominal() {
        assertEquals(10000L, tcCoupons.getFirst().getCouponNominal());
    }

    @Test
    void testGetMinimumBuy() {
        assertEquals(50000L, tcCoupons.getFirst().getMinimumBuy());
    }

    @Test
    void testGetCouponIsUsed() {
        assertFalse(tcCoupons.getFirst().isUsed);
    }

}

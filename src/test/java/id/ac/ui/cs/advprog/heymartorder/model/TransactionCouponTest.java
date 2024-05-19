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

        TransactionCoupon transactionCoupon1 = transactionCouponFactory
                .orderCoupon(1L, "Kupon 4.4.24",
                        10000L, 50000L);

        TransactionCoupon transactionCoupon2 = transactionCouponFactory
                .orderCoupon(1L, "Kupon Ramadhan Sale",
                        50000L, 100000L);


        tcCoupons.add(transactionCoupon1);
        tcCoupons.add(transactionCoupon2);

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


}

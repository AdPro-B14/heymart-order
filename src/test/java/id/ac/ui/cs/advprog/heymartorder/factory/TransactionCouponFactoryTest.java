package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TransactionCouponFactoryTest {

    private List<TransactionCoupon> tcCoupons;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCouponNominalZero() {
        assertThrows(IllegalArgumentException.class, ()-> {
            TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
            TransactionCoupon transactionCoupon1 = transactionCouponFactory
                    .orderCoupon(1L, "Kupon 4.4.24",
                            0L, 50000L);
        });
    }

    @Test
    void testMinimumBuyZero() {
        assertThrows(IllegalArgumentException.class, ()-> {
            TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
            TransactionCoupon transactionCoupon1 = transactionCouponFactory
                    .orderCoupon(1L, "Kupon 4.4.24",
                            10000L, 0L);
        });
    }

    @Test
    void testMinimumBuySmallerThanCouponNominal() {
        assertThrows(IllegalArgumentException.class, ()-> {
            TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
            TransactionCoupon transactionCoupon1 = transactionCouponFactory
                    .orderCoupon(1L, "Kupon 4.4.24",
                            50000L, 20000L);
        });
    }

    @Test
    void testCouponNameNull() {
        assertThrows(IllegalArgumentException.class, ()-> {
            TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
            TransactionCoupon transactionCoupon1 = transactionCouponFactory
                    .orderCoupon(1L, null,
                            10000L, 20000L);
        });
    }
}
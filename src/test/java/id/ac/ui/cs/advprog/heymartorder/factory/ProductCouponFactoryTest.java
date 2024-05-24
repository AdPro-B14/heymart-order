package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ProductCouponFactoryTest {

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
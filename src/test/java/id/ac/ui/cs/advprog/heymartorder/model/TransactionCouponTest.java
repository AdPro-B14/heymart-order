package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testSetMinimumBuy() {
        tcCoupons.getFirst().setMinimumBuy(45000L);
        assertEquals(45000L, tcCoupons.getFirst().getMinimumBuy());
    }

    @Test
    public void testEqualsSameObj() {
        TransactionCoupon tc = tcCoupons.getFirst(); // sut == system under test
        assertTrue (tc.equals(tc));
    }

    @Test
    public void testEqualsNull() {
        TransactionCoupon tc = tcCoupons.getFirst(); // sut == system under test
        assertFalse(tc.equals(null));
    }

    @Test
    void testNoArgsInstance() {
        TransactionCoupon transactionCoupon = new TransactionCoupon();
        assertTrue(transactionCoupon instanceof Coupon);
    }

    @Test
    void testToString() {
        assertTrue(tcCoupons.getFirst().toString().contains("TransactionCoupon("));
    }
}

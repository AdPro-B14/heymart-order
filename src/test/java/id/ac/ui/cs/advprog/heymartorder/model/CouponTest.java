package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class CouponTest {

    private List<TransactionCoupon> tcCoupons;
    @BeforeEach
    void setUp() {
        this.tcCoupons = new ArrayList<>();

        CouponFactory transactionCouponFactory = new TransactionCouponFactory();

        TransactionCoupon transactionCoupon1 = (TransactionCoupon) transactionCouponFactory.orderCoupon();
        transactionCoupon1.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        transactionCoupon1.setCouponName("Kupon 4.4.24");
        transactionCoupon1.setCouponNominal(10000L);
        transactionCoupon1.setMinimumBuy(50000L);
        transactionCoupon1.setUsed(false);


        TransactionCoupon transactionCoupon2 = (TransactionCoupon) transactionCouponFactory.orderCoupon();
        transactionCoupon2.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        transactionCoupon2.setCouponName("Kupon Ramadhan Sale");
        transactionCoupon2.setCouponNominal(50000L);
        transactionCoupon2.setMinimumBuy(100000L);
        transactionCoupon2.setUsed(false);

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
        assertFalse(tcCoupons.getFirst().used);
    }

}

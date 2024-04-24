package id.ac.ui.cs.advprog.heymartorder.model;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {

    @BeforeEach
    void setUp() {
        this.coupons = new ArrayList<>();
        CouponFactory transactionCouponFactory = new TransactionCouponFactory();
        Coupon transactionCoupon = new transactionCouponFactory.orderCoupon()
                .setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd6").setName("Kupon 4.4.24")
                .setNominal(50000L);
    }

    @Test
    void testGetCouponId() {
        assertEquals(5, transactionCoupon.getId);
    }

    @Test
    void testGetCouponName() {
        assertEquals("Kupon 4.4.24", transactionCoupon.getName());
    }

    @Test
    void testGetCouponNominal() {
        assertEquals(50000L, transactionCoupon.getNominal());
    }

}

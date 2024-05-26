package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
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
    }

    @Test
    void testCouponNominalZero() {
        assertThrows(IllegalArgumentException.class, ()-> {
            ProductCouponFactory productCouponFactory = new ProductCouponFactory();
            ProductCoupon productCoupon1 = productCouponFactory
                    .orderCoupon(1L, "Kupon 4.4.24",
                            0L, "eb558e9f-1c39-460e-8860-71af6af63bd6");
        });
    }

    @Test
    void testCouponNameNull() {
        assertThrows(IllegalArgumentException.class, ()-> {
            ProductCouponFactory productCouponFactory = new ProductCouponFactory();
            ProductCoupon productCoupon1 = productCouponFactory
                    .orderCoupon(1L, null,
                            10000L, "eb558e9f-1c39-460e-8860-71af6af63bd6");
        });
    }

}
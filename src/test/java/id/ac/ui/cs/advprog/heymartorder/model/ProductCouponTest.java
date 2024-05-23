package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductCouponTest {
    private List<ProductCoupon> tcCoupons;
    @BeforeEach
    void setUp() {
        this.tcCoupons = new ArrayList<>();

        ProductCouponFactory productCouponFactory = new ProductCouponFactory();

        ProductCoupon productCoupon1 = productCouponFactory
                .orderCoupon(1L, "Kupon Pisang",
                        10000L, "eb558e9f-1c39-460e-8860-71af6af63bd6");

        ProductCoupon productCoupon2 = productCouponFactory
                .orderCoupon(1L, "Kupon Indomie",
                        1000L, "eb558e9f-1c39-460e-8860-71af6af63bd7");


        tcCoupons.add(productCoupon1);
        tcCoupons.add(productCoupon2);

    }

    @Test
    void testGetCouponName() {
        assertEquals("Kupon Pisang", tcCoupons.getFirst().getCouponName());
    }

    @Test
    void testGetCouponNominal() {
        assertEquals(10000L, tcCoupons.getFirst().getCouponNominal());
    }

    @Test
    void testGetProductId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", tcCoupons.getFirst().getProductId());
    }

}

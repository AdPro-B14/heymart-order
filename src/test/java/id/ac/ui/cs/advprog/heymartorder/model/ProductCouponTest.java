package id.ac.ui.cs.advprog.heymartorder.model;

import id.ac.ui.cs.advprog.heymartorder.dto.AddProductCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void testGetSupermarketId() {
        assertEquals(1L, tcCoupons.getFirst().getSupermarketId());
    }


    @Test
    void testSetCouponName() {
        tcCoupons.getFirst().setCouponName("Nutella");
        assertEquals("Nutella", tcCoupons.getFirst().getCouponName());
    }

    @Test
    void testSetCouponNominal() {
        tcCoupons.getFirst().setCouponNominal(15000L);
        assertEquals(15000L, tcCoupons.getFirst().getCouponNominal());
    }

    @Test
    void testSetProductId() {
        tcCoupons.getFirst().setProductId("eb558e9f-1c39-460e-8860-71af6af63bd0");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd0", tcCoupons.getFirst().getProductId());
    }

    @Test
    void testSetSupermarketId() {
        tcCoupons.getFirst().setSupermarketId(2L);
        assertEquals(2L, tcCoupons.getFirst().getSupermarketId());
    }

    @Test
    void testSetAndGetCouponId() {
        tcCoupons.getFirst().setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd1");
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd1", tcCoupons.getFirst().getCouponId());
    }

    @Test
    public void testEqualsSameObj() {
        ProductCoupon tc = tcCoupons.getFirst(); // sut == system under test
        assertTrue (tc.equals(tc));
    }

    @Test
    public void testEqualsNull() {
        ProductCoupon tc = tcCoupons.getFirst(); // sut == system under test
        assertFalse(tc.equals(null));
    }
    
    @Test
    void testNoArgsInstance() {
        ProductCoupon productCoupon = new ProductCoupon();
        assertTrue(productCoupon instanceof Coupon);
    }

    @Test
    void testToString() {
        assertTrue(tcCoupons.getFirst().toString().contains("ProductCoupon("));
    }

}

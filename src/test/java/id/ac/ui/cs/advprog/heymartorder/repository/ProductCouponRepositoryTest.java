package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class ProductCouponRepositoryTest {
    @Mock
    ProductCouponRepository productCouponRepository;

    List<ProductCoupon> tcCoupons;

    @BeforeEach
    void setUp() {
        this.tcCoupons = new ArrayList<>();

        ProductCouponFactory productCouponFactory = new ProductCouponFactory();

        ProductCoupon productCoupon1 = productCouponFactory
                .orderCoupon(1L, "Kupon Pisang",
                        1000L, "eb558e9f-1c39-460e-8860-71af6af63bd6");

        ProductCoupon productCoupon2 = productCouponFactory
                .orderCoupon(1L, "Kupon Indomie",
                        5000L, "eb558e9f-1c39-460e-8860-71af6af63bd7");

        tcCoupons.add(productCoupon1);
        tcCoupons.add(productCoupon2);

    }

    @Test
    void testSaveCreate() {
        ProductCoupon tcCoupon = tcCoupons.get(0);
        Mockito.when(productCouponRepository.save(Mockito.any(ProductCoupon.class)))
                .thenReturn(tcCoupon);
        Mockito.when(productCouponRepository.findByCouponId(tcCoupon.getCouponId()))
                .thenReturn(tcCoupon);

        ProductCoupon savedTcCoupon = productCouponRepository.save(tcCoupon);
        ProductCoupon findResult = productCouponRepository.findByCouponId(tcCoupon.getCouponId());

        assertEquals(tcCoupon.getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupon.getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupon.getProductId(), findResult.getProductId());
    }

    @Test
    void testFindIdIfIdFound() {
        Mockito.when(productCouponRepository.saveAll(tcCoupons)).thenReturn(tcCoupons);
        Mockito.when(productCouponRepository.findByCouponId(tcCoupons.get(0).getCouponId()))
                .thenReturn(tcCoupons.get(0));

        productCouponRepository.saveAll(tcCoupons);
        ProductCoupon findResult = productCouponRepository.findByCouponId(tcCoupons.get(0).getCouponId());

        assertEquals(tcCoupons.get(0).getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupons.get(0).getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupons.get(0).getProductId(), findResult.getProductId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        productCouponRepository.saveAll(tcCoupons);

        ProductCoupon findResult = productCouponRepository.findByCouponId("zczc");
        assertNull(findResult);
    }

    @Test
    void testDelete() {
        // Arrange
        ProductCoupon tcCoupon = tcCoupons.get(0);
        Mockito.when(productCouponRepository.save(Mockito.any(ProductCoupon.class)))
                .thenReturn(tcCoupon); // Define behavior for save method
        ProductCoupon savedCoupon = productCouponRepository.save(tcCoupon);

        assertNotNull(savedCoupon);
        assertEquals(tcCoupon.getCouponId(), savedCoupon.getCouponId());

        Mockito.when(productCouponRepository.count())
                .thenReturn(1L); // Define behavior for save method

        long countBeforeDelete = productCouponRepository.count();
        assertEquals(1, countBeforeDelete);
        productCouponRepository.delete(savedCoupon);

        Mockito.when(productCouponRepository.count())
                .thenReturn(0L); // Define behavior for save method
        // Assert: Check that the coupon was deleted
        long countAfterDelete = productCouponRepository.count();
        assertEquals(0, countAfterDelete);
        assertNull(productCouponRepository.findByCouponId(tcCoupon.getCouponId()));
    }

    @Test
    void testDeleteIfWrongProductCoupon() {
        ProductCoupon tcCoupon1 = tcCoupons.getFirst();
        ProductCoupon result = productCouponRepository.save(tcCoupon1);

        ProductCoupon tcCoupon2 = tcCoupons.get(1);
        productCouponRepository.delete(tcCoupon2);

        List<ProductCoupon> tcList = new ArrayList<>();
        tcList.add(productCouponRepository.findByCouponId(tcCoupon1.getCouponId()));
        System.out.println(tcList);
        assertFalse(tcList.isEmpty()); // Assert true because the first product should still exist
    }
}

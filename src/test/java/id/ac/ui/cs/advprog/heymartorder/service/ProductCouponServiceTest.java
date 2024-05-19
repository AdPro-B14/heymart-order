package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.ProductCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ProductCouponServiceTest {
    @InjectMocks
    ProductCouponServiceImpl productCouponService;
    @Mock
    ProductCouponRepository productCouponRepository;
    List<ProductCoupon> tcCoupons;

    @BeforeEach
    void setUp() {
        tcCoupons = new ArrayList<>();

        ProductCouponFactory productCouponFactory = new ProductCouponFactory();

        ProductCoupon productCoupon1 = productCouponFactory
                .orderCoupon(1L, "Kupon 4.4.24",
                        10000L, 50000L);

        ProductCoupon productCoupon2 = productCouponFactory
                .orderCoupon(1L, "Kupon Ramadhan Sale",
                        50000L, 100000L);

        tcCoupons.add(productCoupon1);
        tcCoupons.add(productCoupon2);
    }

    @Test
    void testCreateProductCoupon() {
        ProductCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(productCouponRepository).save(tcCoupon);

        ProductCoupon result = productCouponService.createProductCoupon(tcCoupon);
        verify(productCouponRepository, times(1)).save(tcCoupon);
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
    }

    @Test
    void testCreateProductCouponNotValid() {
        assertThrows(IllegalArgumentException.class, () -> productCouponService.createProductCoupon(null));
    }


    @Test
    void testFindIdIfIdFound() {
        ProductCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(productCouponRepository).findByCouponId(tcCoupon.getCouponId());

        ProductCoupon result = productCouponService.findById(tcCoupon.getCouponId());
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        doReturn(null).when(productCouponRepository).findByCouponId("zczc");
        assertNull(productCouponRepository.findByCouponId("zczc"));
    }

    @Test
    void testFindAll() {
        ProductCoupon tcCoupon = tcCoupons.get(1);
        doReturn(tcCoupons).when(productCouponRepository).findAll();

        List<ProductCoupon> results = productCouponService.findAll();
        for (int i = 0; i< results.size(); i++) {
            assertEquals(tcCoupons.get(i).getCouponId(), results.get(i).getCouponId());
        }
        assertEquals(2, results.size());
    }

    @Test
    void deleteProductCouponInvalid() {
        assertThrows(IllegalArgumentException.class, () -> productCouponService.delete(null));
    }
}

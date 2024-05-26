package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.AddProductCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.ProductCouponService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductCouponControllerTest {
    @Mock
    private ProductCouponService productCouponService;

    @Mock
    private JwtService jwtService;


    @InjectMocks
    private ProductCouponController productCouponController;

    List<ProductCoupon> tcCoupons;
    ProductCouponFactory productCouponFactory = new ProductCouponFactory();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ProductCoupon productCoupon1 = productCouponFactory
                .orderCoupon(1L, "Kupon 4.4.24",
                        10000L, "36fc4221-63b2-4fe6-8ce7-a267da361138");
        productCoupon1.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd0");

        ProductCoupon productCoupon2 = productCouponFactory
                .orderCoupon(2L, "Kupon Ramadhan Sale",
                        5000L, "82e55585-8d26-4b44-9592-4e549192ab7f");
        productCoupon2.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd1");

        tcCoupons = new ArrayList<>();
        tcCoupons.add(productCoupon1);
        tcCoupons.add(productCoupon2);

    }

    @Test
    void testGetByCouponId() {
        String couponId = "eb558e9f-1c39-460e-8860-71af6af63bd0";

        when(productCouponService.findById(couponId)).thenReturn(tcCoupons.getFirst());

        ResponseEntity<ProductCoupon> response = productCouponController.getProductCoupon(couponId);

        assertEquals(ResponseEntity.ok(tcCoupons.getFirst()), response);
        verify(productCouponService, times(1)).findById(couponId);
    }

    @Test
    void testGetBySupermarketId() {
        Long supermarketId = 1L;
        List<ProductCoupon> supermarketCoupons = new ArrayList<>();
        supermarketCoupons.add(tcCoupons.getFirst());

        when(productCouponService.findBySupermarketId(supermarketId)).thenReturn(supermarketCoupons);

        ResponseEntity<List<ProductCoupon>> response = productCouponController.getSupermarketProductCoupons(supermarketId);

        assertEquals(ResponseEntity.ok(supermarketCoupons), response);
        verify(productCouponService, times(1)).findBySupermarketId(supermarketId);
    }

    @Test
    void testGetAll() {
        when(productCouponService.findAll()).thenReturn(tcCoupons);

        ResponseEntity<List<ProductCoupon>> response = productCouponController.getAllProductCoupons();

        assertEquals(ResponseEntity.ok(tcCoupons), response);
        verify(productCouponService, times(1)).findAll();
    }


}

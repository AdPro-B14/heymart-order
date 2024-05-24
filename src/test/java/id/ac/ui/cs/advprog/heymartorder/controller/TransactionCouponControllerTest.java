package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.AddTransactionCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
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

public class TransactionCouponControllerTest {
    @Mock
    private TransactionCouponService transactionCouponService;

    @Mock
    private JwtService jwtService;


    @InjectMocks
    private TransactionCouponController transactionCouponController;

    List<TransactionCoupon> tcCoupons;
    TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        TransactionCoupon transactionCoupon1 = transactionCouponFactory
                .orderCoupon(1L, "Kupon 4.4.24",
                        10000L, 50000L);
        transactionCoupon1.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd0");

        TransactionCoupon transactionCoupon2 = transactionCouponFactory
                .orderCoupon(2L, "Kupon Ramadhan Sale",
                        50000L, 100000L);
        transactionCoupon2.setCouponId("eb558e9f-1c39-460e-8860-71af6af63bd1");

        tcCoupons = new ArrayList<>();
        tcCoupons.add(transactionCoupon1);
        tcCoupons.add(transactionCoupon2);

    }

    @GetMapping("/transaction-coupon/{id}")
    public ResponseEntity<TransactionCoupon> getTransactionCoupon(@PathVariable("id") String couponId) {
        TransactionCoupon tcCoupon = transactionCouponService.findById(couponId);
        return ResponseEntity.ok(tcCoupon);
    }
    @Test
    void testGetByCouponId() {
        String couponId = "eb558e9f-1c39-460e-8860-71af6af63bd0";

        when(transactionCouponService.findById(couponId)).thenReturn(tcCoupons.getFirst());

        ResponseEntity<TransactionCoupon> response = transactionCouponController.getTransactionCoupon(couponId);

        assertEquals(ResponseEntity.ok(tcCoupons.getFirst()), response);
        verify(transactionCouponService, times(1)).findById(couponId);
    }

    @Test
    void testGetBySupermarketId() {
        Long supermarketId = 1L;
        List<TransactionCoupon> supermarketCoupons = new ArrayList<>();
        supermarketCoupons.add(tcCoupons.getFirst());

        when(transactionCouponService.findBySupermarketId(supermarketId)).thenReturn(supermarketCoupons);

        ResponseEntity<List<TransactionCoupon>> response = transactionCouponController.getSupermarketTransactionCoupons(supermarketId);

        assertEquals(ResponseEntity.ok(supermarketCoupons), response);
        verify(transactionCouponService, times(1)).findBySupermarketId(supermarketId);
    }

    @Test
    void testGetAll() {
        when(transactionCouponService.findAll()).thenReturn(tcCoupons);

        ResponseEntity<List<TransactionCoupon>> response = transactionCouponController.getAllTransactionCoupons();

        assertEquals(ResponseEntity.ok(tcCoupons), response);
        verify(transactionCouponService, times(1)).findAll();
    }


}

package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.AddTransactionCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.dto.AddTransactionCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.dto.SuccessResponse;
import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
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

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testCreateTransactionCouponSuccess() throws IllegalAccessException {
        String token = "validToken";
        String id = "Bearer " + token;
        Long managerId = 1L;
        AddTransactionCouponRequest request = AddTransactionCouponRequest.builder()
                .supermarketId(123L)
                .couponName("Murah meriah")
                .couponNominal(1000L)
                .minimumBuy(50000L)
                .build();

        when(jwtService.extractUserId(token)).thenReturn(managerId);
        when(jwtService.extractRole(token)).thenReturn("manager");

        TransactionCoupon tcCoupon = transactionCouponFactory.orderCoupon(request.supermarketId, request.couponName,
                request.couponNominal,request.minimumBuy);
        when(transactionCouponService.createTransactionCoupon(tcCoupon)).thenReturn(tcCoupon);

        ResponseEntity<TransactionCoupon> responseEntity = transactionCouponController.addTransactionCoupon(token, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tcCoupon, responseEntity.getBody());
    }

    @Test
    void testCreateTransactionCouponUnauthorizedAccess() {
        String token = "invalidToken";
        String id = "Bearer " + token;

        AddTransactionCouponRequest request = AddTransactionCouponRequest.builder()
                .supermarketId(123L)
                .couponName("Discount 2024")
                .couponNominal(10000L)
                .minimumBuy(50000L)
                .build();
        when(jwtService.extractRole(token)).thenReturn("customer");

        assertThrows(IllegalAccessException.class, () -> transactionCouponController.addTransactionCoupon(id, request));
    }

    @Test
    void testDeleteTransactionCoupon() throws IllegalAccessException {
        String token = "validToken";
        String id = "Bearer " + token;
        Long managerId = 1L;
        when(jwtService.extractUserId(token)).thenReturn(managerId);
        when(jwtService.extractRole(token)).thenReturn("manager");

        TransactionCoupon pCoupon = tcCoupons.getFirst();

        ResponseEntity<SuccessResponse> responseEntity = transactionCouponController.deleteTransactionCoupon(token, pCoupon.getCouponId());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionCouponService, times(1)).delete(pCoupon.getCouponId());
    }

    @Test
    void testDeleteTransactionCouponUnauthorizedAccess() throws IllegalAccessException {
        String token = "invalidToken";
        String id = "Bearer " + token;
        Long customerId = 1L;
        when(jwtService.extractUserId(token)).thenReturn(customerId);
        when(jwtService.extractRole(token)).thenReturn("customer");

        TransactionCoupon tcCoupon = tcCoupons.getFirst();

        assertThrows(IllegalAccessException.class, () -> transactionCouponController.deleteTransactionCoupon(token, tcCoupon.getCouponId()));
    }

    @Test
    void testToStringForBuilder() {
        assertTrue(AddTransactionCouponRequest.builder().toString().contains("AddTransactionCouponRequest.AddTransactionCouponRequestBuilder"));
    }

    @Test
    void testToStringForSuccessResponseBuilder() {
        assertTrue(SuccessResponse.builder().toString().contains("SuccessResponse.SuccessResponseBuilder"));
    }
}

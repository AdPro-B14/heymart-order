package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.SuccessResponse;
import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymartorder.dto.AddTransactionCouponRequest;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class TransactionCouponController {

    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final JwtService jwtService;

    private final WebClient webClient;

    private TransactionCouponService transactionCouponService;

    public TransactionCouponController(JwtService jwtService, WebClient webClient) {
        this.jwtService = jwtService;
        this.webClient = webClient;
    }

    @GetMapping("/all-transaction-coupon")
    public ResponseEntity<List<TransactionCoupon>> allTransactionCoupon() {
        List<TransactionCoupon> allTransactionCoupon = transactionCouponService.findAll();
        return ResponseEntity.ok(allTransactionCoupon);
    }

    @PostMapping("/create-transaction-coupon")
    public ResponseEntity<TransactionCoupon> addTransactionCoupon(@RequestHeader(value = "Authorization") String id,
                                                                  @RequestBody AddTransactionCouponRequest request) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
        TransactionCoupon tcCoupon = transactionCouponFactory
                .orderCoupon(request.couponId, request.couponName, request.couponNominal, request.isUsed, request.minimumBuy);

        return ResponseEntity.ok(transactionCouponService.createTransactionCoupon(tcCoupon));
    }

    @DeleteMapping("/delete-transaction-coupon/{id}")
    public ResponseEntity<SuccessResponse> deleteSupermarket(@RequestHeader(value = "Authorization") String id,
                                                             @PathVariable("id") String couponId) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        transactionCouponService.delete(couponId);
        return ResponseEntity.ok(SuccessResponse.builder().success(true).build());    }
}
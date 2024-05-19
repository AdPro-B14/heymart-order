package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.SuccessResponse;
import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/transaction-coupon")
@RequiredArgsConstructor
public class TransactionCouponController {

    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final JwtService jwtService;

    private final TransactionCouponService transactionCouponService;



    @GetMapping("/transaction-coupon/{id}")
    public ResponseEntity<TransactionCoupon> getTransactionCoupon(@PathVariable("id") String couponId) {
        TransactionCoupon tcCoupon = transactionCouponService.findById(couponId);
        return ResponseEntity.ok(tcCoupon);
    }

    @GetMapping("/supermarket-transaction-coupon/{id}")
    public ResponseEntity<List<TransactionCoupon>> getSupermarketTransactionCoupons(@PathVariable("id") Long supermarketId) {
        List<TransactionCoupon> allTransactionCoupon = transactionCouponService.findBySupermarketId(supermarketId);
        return ResponseEntity.ok(allTransactionCoupon);
    }

    // supermarketId /{id}
    @GetMapping("/all-transaction-coupon")
    public ResponseEntity<List<TransactionCoupon>> getAllTransactionCoupons() {
        List<TransactionCoupon> allTransactionCoupon = transactionCouponService.findAll();
        return ResponseEntity.ok(allTransactionCoupon);
    }


    // supermarketId
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
                .orderCoupon(request.supermarketId, request.couponName, request.couponNominal, request.minimumBuy);

        return ResponseEntity.ok(transactionCouponService.createTransactionCoupon(tcCoupon));
    }

    @DeleteMapping("/delete-transaction-coupon/{id}")
    public ResponseEntity<SuccessResponse> deleteTransactionCoupon(@RequestHeader(value = "Authorization") String id,
                                                             @PathVariable("id") String couponId) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        transactionCouponService.delete(couponId);
        return ResponseEntity.ok(SuccessResponse.builder().success(true).build());
    }

    @GetMapping("/transaction-coupon-consumers/{id}")
    public ResponseEntity<List<Long>> getTransactionCouponConsumers(@RequestHeader(value = "Authorization") String id,
                                                                    @PathVariable("id") String couponId) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        return ResponseEntity.ok(transactionCouponService.getConsumers(couponId));
    }

    // tanya dulu lagi, pake/ngga?
//    @GetMapping("/transaction-coupon-validation") //gaperlu dto, response aja
//    public ResponseEntity<SuccessResponse> validateCoupon(@RequestParam("couponId") String couponId, @RequestParam("consumerId") Long consumerId) {
//        return ResponseEntity.ok(SuccessResponse.builder().success(true).build());
//    }

//    // validasi make service validate consumer
//    @GetMapping("/transaction-coupon-validation")
//    public ResponseEntity<Boolean> validateCoupon(@RequestParam("couponId") String couponId, @RequestParam("consumerId") Long consumerId) {
//        return ResponseEntity.ok(transactionCouponService.validateConsumer(couponId, consumerId));
//    }
}
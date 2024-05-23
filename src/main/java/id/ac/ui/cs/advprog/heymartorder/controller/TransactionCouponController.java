package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymartorder.dto.AddTransactionCouponRequest;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class TransactionCouponController {

    @Autowired
    private TransactionCouponService transactionCouponService;

    @GetMapping("/all-transaction-coupon")
    public ResponseEntity<List<TransactionCoupon>> allTransactionCoupon() {
        List<TransactionCoupon> allTransactionCoupon = transactionCouponService.findAll();
        return ResponseEntity.ok(allTransactionCoupon);
    }
    @PostMapping("/create-transaction-coupon")
    public ResponseEntity<TransactionCoupon> addTransactionCoupon(@RequestBody AddTransactionCouponRequest tcCoupon) {
        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();

        TransactionCoupon transactionCoupon1 = transactionCouponFactory
                .orderCoupon(tcCoupon.couponId, tcCoupon.couponName,
                        tcCoupon.couponNominal, tcCoupon.isUsed, tcCoupon.minimumBuy);

        TransactionCoupon savedTcCoupon = transactionCouponService.createTransactionCoupon(transactionCoupon1);
        return new ResponseEntity<>(savedTcCoupon, HttpStatus.OK);
    }   
}
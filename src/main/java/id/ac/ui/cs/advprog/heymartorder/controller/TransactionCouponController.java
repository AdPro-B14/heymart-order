package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
}
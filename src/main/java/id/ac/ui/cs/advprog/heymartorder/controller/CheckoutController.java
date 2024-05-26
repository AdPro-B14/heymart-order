package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.service.CheckoutService;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import id.ac.ui.cs.advprog.heymartorder.dto.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final JwtService jwtService;
    private final CheckoutService checkoutService;

    @PostMapping("")
    public ResponseEntity<Long> checkout(
            @RequestHeader(value = "Authorization") String token,
            @RequestBody CheckoutRequest checkoutRequest) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long userId = jwtService.extractUserId(jwt);
        Long total = checkoutService.checkoutWithCoupon(userId, jwt, checkoutRequest.getProductCouponIds(), checkoutRequest.getTransactionCouponId());
        return ResponseEntity.ok(total);
    }
}

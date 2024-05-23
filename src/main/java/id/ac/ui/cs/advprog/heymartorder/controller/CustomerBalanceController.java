package id.ac.ui.cs.advprog.heymartorder.controller;
import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.service.CustomerBalanceService;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customer-balance")
@RequiredArgsConstructor
public class CustomerBalanceController {
    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final CustomerBalanceService customerBalanceService;
    private final JwtService jwtService;

    private final WebClient webClient;

    @PostMapping("/create/")
    public ResponseEntity<CustomerBalance> createBalance (@RequestHeader(value="Authorization") String id)
            throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        Long customerId = jwtService.extractUserId(token);
        if (!jwtService.extractRole(token).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        if (!customerBalanceService.existsCustomerBalanceById(customerId)) {
            return ResponseEntity.ok(customerBalanceService.createCustomerBalance(customerId));
        }
        return ResponseEntity.ok(customerBalanceService.findCustomerBalanceById(customerId));
    }

    @GetMapping("/{id}/get-balance-amount/")
    public ResponseEntity<BigDecimal> getBalanceAmountByUserId (@RequestHeader(value = "Authorization") String id,
                                                                @PathVariable("id") Long userId) throws IllegalAccessException{
        BigDecimal amount = customerBalanceService.getCustomerBalanceAmountById(userId);
        return ResponseEntity.ok(amount);
    }

    @PutMapping("/top-up")
    public ResponseEntity<CustomerBalance> topUpBalance (@RequestHeader(value = "Authorization") String id,
                                                    @RequestBody TopUpBalanceRequest request) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long customerId = jwtService.extractUserId(token);
        CustomerBalance customerBalance = customerBalanceService.findCustomerBalanceById(customerId);
        customerBalanceService.topUp(customerId, request.getAmount());
        return ResponseEntity.ok(customerBalance);
    }
}

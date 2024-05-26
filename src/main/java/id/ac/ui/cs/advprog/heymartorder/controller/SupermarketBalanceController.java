package id.ac.ui.cs.advprog.heymartorder.controller;
import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.rest.UserService;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.SupermarketBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/supermarket-balance")
@RequiredArgsConstructor
public class SupermarketBalanceController {
    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final SupermarketBalanceService supermarketBalanceService;
    private final JwtService jwtService;
    private final UserService userService;

    private final WebClient webClient;

    @PostMapping("/create/{id}")
    public ResponseEntity<SupermarketBalance> createBalance (@RequestHeader(value="Authorization") String id,
                                                          @PathVariable("id") Long supermarketId) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }
        if (!supermarketBalanceService.existsSupermarketBalanceById(supermarketId)) {
            return ResponseEntity.ok(supermarketBalanceService.createSupermarketBalance(supermarketId));
        }
        return ResponseEntity.ok(supermarketBalanceService.findSupermarketBalanceById(supermarketId));
    }
    
    @GetMapping("/get-balance/{id}")
    public ResponseEntity<SupermarketBalance> getBalanceBySupermarketId (@RequestHeader(value = "Authorization") String id,
                                                          @PathVariable("id") Long supermarketId) throws IllegalAccessException{
        SupermarketBalance supermarketBalance = supermarketBalanceService.findSupermarketBalanceById(supermarketId);
        return ResponseEntity.ok(supermarketBalance);
    }

    @GetMapping("/get-manager-balance/")
    public ResponseEntity<SupermarketBalance> getBalanceBySupermarketIdForManager (@RequestHeader(value = "Authorization") String id) throws IllegalAccessException{
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long supermarketId = userService.getProfile(token).manager_supermarket_id;
        SupermarketBalance supermarketBalance = supermarketBalanceService.findSupermarketBalanceById(supermarketId);
        return ResponseEntity.ok(supermarketBalance);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<SupermarketBalance> withdrawBalance(@RequestHeader(value = "Authorization") String id,
                                                      @RequestBody WithdrawBalanceRequest request)
                                                        throws IllegalAccessException, IllegalArgumentException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long supermarketId = userService.getProfile(token).manager_supermarket_id;
        SupermarketBalance supermarketBalance = supermarketBalanceService.
                withDraw(supermarketId, request.getAmount());
        return ResponseEntity.ok(supermarketBalance);
    }
}

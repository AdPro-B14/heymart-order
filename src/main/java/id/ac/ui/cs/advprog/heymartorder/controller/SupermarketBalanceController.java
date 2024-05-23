package id.ac.ui.cs.advprog.heymartorder.controller;
import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
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
    
    @GetMapping("/{id}/get-balance")
    public ResponseEntity<BigDecimal> getBalanceBySupermarketId (@RequestHeader(value = "Authorization") String id,
                                                          @PathVariable("id") Long supermarketId) throws IllegalAccessException{
        BigDecimal amount = supermarketBalanceService.getSupermarketBalanceAmountById(supermarketId);
        return ResponseEntity.ok(amount);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<SupermarketBalance> withdrawBalance(@RequestHeader(value = "Authorization") String id, @PathVariable("id") Long supermarketId,
                                                      @RequestBody WithdrawBalanceRequest request)
                                                        throws IllegalAccessException, IllegalArgumentException {
        String token = id.replace("Bearer ", "");
        String managerEmail = jwtService.extractEmail(token);
        GetSupermarketResponse supermarketResponse = webClient.get()
                .uri(GATEWAY_URL + "/api/store/supermarket/supermarket",
                        uriBuilder -> uriBuilder.queryParam("id", supermarketId).build())
                .retrieve()
                .bodyToMono(GetSupermarketResponse.class)
                .block();
        assert supermarketResponse != null;
        List<String> managers = supermarketResponse.getManagerEmails();
        boolean userManageSupermarket = managers.contains(managerEmail);
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                || !userManageSupermarket) {
            throw new IllegalAccessException("You have no access.");
        }
        SupermarketBalance supermarketBalance = supermarketBalanceService.
                withDraw(supermarketId, request.getAmount());
        return ResponseEntity.ok(supermarketBalance);
    }
}

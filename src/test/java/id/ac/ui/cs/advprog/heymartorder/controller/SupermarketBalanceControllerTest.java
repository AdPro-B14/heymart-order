package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.WithdrawBalanceRequest;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.SupermarketBalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupermarketBalanceControllerTest {

    @Mock
    private SupermarketBalanceService supermarketBalanceService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private SupermarketBalanceController supermarketBalanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetBalanceBySupermarketId() throws IllegalAccessException {
        String token = "validToken";
        String id = "Bearer " + token;
        Long supermarketId = 1L;
        BigDecimal balanceAmount = BigDecimal.valueOf(100.00);

        when(supermarketBalanceService.getSupermarketBalanceAmountById(supermarketId)).thenReturn(balanceAmount);

        ResponseEntity<BigDecimal> response = supermarketBalanceController.getBalanceBySupermarketId(id, supermarketId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(balanceAmount, response.getBody());
    }
}

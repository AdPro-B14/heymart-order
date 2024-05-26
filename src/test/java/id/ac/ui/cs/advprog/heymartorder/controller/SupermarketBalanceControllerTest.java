package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.WithdrawBalanceRequest;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
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
    public void testGetBalanceBySupermarketId() throws IllegalAccessException {
        String token = "Bearer valid_token";
        Long supermarketId = 1L;
        SupermarketBalance expectedBalance = supermarketBalanceService.createSupermarketBalance(supermarketId);
        when(supermarketBalanceService.findSupermarketBalanceById(supermarketId)).thenReturn(expectedBalance);
        when(jwtService.extractRole("valid_token")).thenReturn("admin");

        ResponseEntity<SupermarketBalance> response = supermarketBalanceController.getBalanceBySupermarketId(token, supermarketId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBalance, response.getBody());
    }
}

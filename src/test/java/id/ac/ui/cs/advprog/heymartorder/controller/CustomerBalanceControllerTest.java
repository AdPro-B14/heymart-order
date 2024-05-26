package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.dto.TopUpBalanceRequest;
import id.ac.ui.cs.advprog.heymartorder.service.CustomerBalanceService;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CustomerBalanceControllerTest {

    @Mock
    private CustomerBalanceService customerBalanceService;

    @Mock
    private JwtService jwtService;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private CustomerBalanceController customerBalanceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBalance_NewCustomer() throws IllegalAccessException {
        String token = "validToken";
        String id = "Bearer " + token;
        Long customerId = 1L;
        CustomerBalance newCustomerBalance = new CustomerBalance();

        when(jwtService.extractUserId(token)).thenReturn(customerId);
        when(jwtService.extractRole(token)).thenReturn("customer");
        when(customerBalanceService.existsCustomerBalanceById(customerId)).thenReturn(false);
        when(customerBalanceService.createCustomerBalance(customerId)).thenReturn(newCustomerBalance);

        ResponseEntity<CustomerBalance> response = customerBalanceController.createBalance(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newCustomerBalance, response.getBody());
        verify(customerBalanceService, times(1)).createCustomerBalance(customerId);
    }

    @Test
    void testCreateBalance_ExistingCustomer() throws IllegalAccessException {
        String token = "validToken";
        String id = "Bearer " + token;
        Long customerId = 1L;
        CustomerBalance existingCustomerBalance = new CustomerBalance();

        when(jwtService.extractUserId(token)).thenReturn(customerId);
        when(jwtService.extractRole(token)).thenReturn("customer");
        when(customerBalanceService.existsCustomerBalanceById(customerId)).thenReturn(true);
        when(customerBalanceService.findCustomerBalanceById(customerId)).thenReturn(existingCustomerBalance);

        ResponseEntity<CustomerBalance> response = customerBalanceController.createBalance(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(existingCustomerBalance, response.getBody());
        verify(customerBalanceService, times(1)).findCustomerBalanceById(customerId);
    }

    @Test
    public void testGetBalanceByUserId() throws IllegalAccessException {
        String token = "Bearer valid_token";
        Long userId = 1L;
        CustomerBalance expectedBalance = customerBalanceService.createCustomerBalance(userId);
        when(customerBalanceService.findCustomerBalanceById(userId)).thenReturn(expectedBalance);
        when(jwtService.extractRole("valid_token")).thenReturn("customer");

        ResponseEntity<CustomerBalance> response = customerBalanceController.getBalance(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBalance, response.getBody());
    }

    @Test
    public void testTopUpBalanceSuccess() throws IllegalAccessException {
        String token = "Bearer validToken";
        Long customerId = 1L;
        BigDecimal amount = new BigDecimal("100.00");
        TopUpBalanceRequest request = new TopUpBalanceRequest();
        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setId(customerId);
        request.setAmount(amount);

        when(jwtService.extractRole(anyString())).thenReturn("customer");
        when(jwtService.extractUserId(anyString())).thenReturn(customerId);
        when(customerBalanceService.topUp(anyLong(), any(BigDecimal.class))).thenReturn(customerBalance);

        ResponseEntity<CustomerBalance> response = customerBalanceController.topUpBalance(token, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerBalance, response.getBody());
    }

    @Test
    public void testTopUpBalanceForbidden() {
        String token = "Bearer invalidToken";
        TopUpBalanceRequest request = new TopUpBalanceRequest();
        request.setAmount(new BigDecimal("100.00"));

        when(jwtService.extractRole(anyString())).thenReturn("manager");

        IllegalAccessException thrown = assertThrows(
                IllegalAccessException.class,
                () -> customerBalanceController.topUpBalance(token, request),
                "Expected topUpBalance to throw, but it didn't"
        );

        assertEquals("You have no access.", thrown.getMessage());
        verify(customerBalanceService, never()).topUp(anyLong(), any(BigDecimal.class));
    }

    @Test
    void testUnauthorizedAccess() {
        String token = "invalidToken";
        String id = "Bearer " + token;

        when(jwtService.extractRole(token)).thenReturn("admin");

        assertThrows(IllegalAccessException.class, () -> customerBalanceController.createBalance(id));
        assertThrows(IllegalAccessException.class, () -> customerBalanceController.topUpBalance(id, new TopUpBalanceRequest()));
    }
}

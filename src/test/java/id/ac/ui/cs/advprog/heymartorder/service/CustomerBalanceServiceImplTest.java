package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.exception.InsufficientBalanceException;
import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.CustomerBalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerBalanceServiceImplTest {
    @InjectMocks
    private CustomerBalanceServiceImpl customerBalanceService;

    @Mock
    private CustomerBalanceRepository customerBalanceRepository;

    @Mock
    private CustomerBalanceStrategy customerBalanceStrategy;

    private List<CustomerBalance> customerBalancesList;

    @BeforeEach
    void setUp() {
        customerBalancesList = new ArrayList<>();

        CustomerBalance customerBalance1 = CustomerBalance.builder()
                .id(1L)
                .customerId(1L)
                .amount(BigDecimal.valueOf(10000))
                .build();

        CustomerBalance customerBalance2 = CustomerBalance.builder()
                .id(2L)
                .customerId(2L)
                .amount(BigDecimal.valueOf(5000))
                .build();

        customerBalancesList.add(customerBalance1);
        customerBalancesList.add(customerBalance2);
    }

    @Test
    void testCreateCustomerBalanceValid() {
        when(customerBalanceStrategy.createBalance(customerBalancesList.getFirst().getCustomerId()))
                .thenReturn(customerBalancesList.getFirst());
        when(customerBalanceStrategy.findBalanceById(customerBalancesList.getFirst().getId()))
                .thenReturn(customerBalancesList.getFirst());

        CustomerBalance customerBalance = customerBalanceService.createCustomerBalance(customerBalancesList.get(0).getCustomerId());

        assertEquals(customerBalance.getId(),
                customerBalanceService.findCustomerBalanceById(customerBalance.getId()).getId());
    }

    @Test
    void testCreateCustomerBalanceNotValid() {
        when(customerBalanceService.createCustomerBalance(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> customerBalanceService.createCustomerBalance(null));
    }

    @Test
    void testDeleteCustomerBalanceValid() {
        customerBalanceService.deleteCustomerBalance(customerBalancesList.getFirst().getId());
    }

    @Test
    void testDeleteCustomerBalanceNotValid() {
        when(customerBalanceService.deleteCustomerBalance(3L)).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> customerBalanceService.deleteCustomerBalance(3L));
        when(customerBalanceService.deleteCustomerBalance(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> customerBalanceService.deleteCustomerBalance(null));
    }

    @Test
    public void testFindCustomerBalanceById() {
        when(customerBalanceStrategy.findBalanceById(1L)).thenReturn(customerBalancesList.get(0));

        CustomerBalance result = customerBalanceService.findCustomerBalanceById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals(BigDecimal.valueOf(10000), result.getAmount());
    }

    @Test
    public void testGetCustomerBalanceAmountById() {
        when(customerBalanceStrategy.getBalanceAmountById(1L)).thenReturn(customerBalancesList.get(0).getAmount());

        BigDecimal result = customerBalanceService.getCustomerBalanceAmountById(1L);

        assertEquals(BigDecimal.valueOf(10000), result);
    }

    @Test
    public void testExistsCustomerBalanceById() {
        when(customerBalanceStrategy.existsById(1L)).thenReturn(true);

        boolean result = customerBalanceService.existsCustomerBalanceById(1L);

        assertTrue(result);
    }

    @Test
    public void testTopUp() {
        when(customerBalanceRepository.save(any(CustomerBalance.class))).thenReturn(customerBalancesList.get(0));
        when(customerBalanceStrategy.findBalanceById(1L)).thenReturn(customerBalancesList.get(0));

        BigDecimal topUpAmount = new BigDecimal("5000");
        CustomerBalance result = customerBalanceService.topUp(1L, topUpAmount);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(15000), result.getAmount());
    }

    @Test
    public void testCalculateAtCheckout() {
        when(customerBalanceStrategy.calculateAtCheckout(1L, 5000L)).thenReturn(customerBalancesList.get(0));

        CustomerBalance result = customerBalanceService.calculateAtCheckout(1L, 5000L);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals(BigDecimal.valueOf(10000), result.getAmount());
    }

    @Test
    public void testCalculateAtCheckoutInsufficientBalance() {
        doThrow(new InsufficientBalanceException()).when(customerBalanceStrategy).calculateAtCheckout(2L, 6000L);

        assertThrows(InsufficientBalanceException.class, () -> {
            customerBalanceService.calculateAtCheckout(2L, 6000L);
        });
    }
}
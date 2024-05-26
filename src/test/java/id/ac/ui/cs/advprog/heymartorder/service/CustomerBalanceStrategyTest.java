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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerBalanceStrategyTest {

    @Mock
    private CustomerBalanceRepository customerBalanceRepository;

    @InjectMocks
    private CustomerBalanceStrategy customerBalanceStrategy;

    private CustomerBalance customerBalance;

    @BeforeEach
    public void setUp() {
        customerBalance = new CustomerBalance();
        customerBalance.setCustomerId(1L);
        customerBalance.setAmount(BigDecimal.TEN);
    }

    @Test
    public void testCreateBalance() {
        CustomerBalance customerBalance2 = new CustomerBalance();
        customerBalance2.setCustomerId(2L);
        customerBalance2.setAmount(BigDecimal.ZERO);
        when(customerBalanceRepository.save(any(CustomerBalance.class))).thenReturn(customerBalance2);

        CustomerBalance result = customerBalanceStrategy.createBalance(2L);

        assertNotNull(result);
        assertEquals(2L, result.getCustomerId());
        assertEquals(BigDecimal.ZERO, result.getAmount());
        verify(customerBalanceRepository, times(1)).save(any(CustomerBalance.class));
    }

    @Test
    public void testDeleteBalance() {
        when(customerBalanceRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerBalance));

        CustomerBalance result = customerBalanceStrategy.deleteBalance(1L);

        assertNotNull(result);
        verify(customerBalanceRepository, times(1)).delete(customerBalance);
    }

    @Test
    public void testFindBalanceById() {
        when(customerBalanceRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerBalance));

        CustomerBalance result = customerBalanceStrategy.findBalanceById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        assertEquals(BigDecimal.TEN, result.getAmount());
    }

    @Test
    public void testExistsById() {
        when(customerBalanceRepository.existsById(1L)).thenReturn(true);

        boolean result = customerBalanceStrategy.existsById(1L);

        assertTrue(result);
    }

    @Test
    public void testGetBalanceAmountById() {
        when(customerBalanceRepository.findByCustomerId(1L)).thenReturn(Optional.of(customerBalance));

        BigDecimal result = customerBalanceStrategy.getBalanceAmountById(1L);

        assertNotNull(result);
        assertEquals(BigDecimal.TEN, result);
    }

    @Test
    public void testCalculateAtCheckout() {
        when(customerBalanceRepository.findById(1L)).thenReturn(Optional.of(customerBalance));

        CustomerBalance result = customerBalanceStrategy.calculateAtCheckout(1L, 5L);

        assertNotNull(result);
        verify(customerBalanceRepository, times(1)).save(customerBalance);
    }

    @Test
    public void testCalculateAtCheckoutInsufficientBalance() {
        when(customerBalanceRepository.findById(1L)).thenReturn(Optional.of(customerBalance));

        assertThrows(InsufficientBalanceException.class, () -> {
            customerBalanceStrategy.calculateAtCheckout(1L, 15L);
        });
    }

    @Test
    public void testCreateBalanceWithNullCustomerId() {
        assertThrows(IllegalArgumentException.class, () -> {
            customerBalanceStrategy.createBalance(null);
        });
    }

    @Test
    public void testFindBalanceByIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            customerBalanceStrategy.findBalanceById(null);
        });
    }

    @Test
    public void testGetBalanceAmountByIdWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            customerBalanceStrategy.getBalanceAmountById(null);
        });
    }

    @Test
    public void testCalculateAtCheckoutWithNullCustomerId() {
        assertThrows(IllegalArgumentException.class, () -> {
            customerBalanceStrategy.calculateAtCheckout(null, 5L);
        });
    }
}

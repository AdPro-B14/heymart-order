package id.ac.ui.cs.advprog.heymartorder.service;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerBalanceServiceImplTest {
    @InjectMocks
    private CustomerBalanceServiceImpl customerBalanceService;

    @Mock
    private CustomerBalanceRepository customerBalanceRepository;

    List<CustomerBalance> customerBalancesList = new ArrayList<>();

    @BeforeEach
    void setUp() {
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
        when(customerBalanceRepository.save(any())).thenReturn(customerBalancesList.getFirst());
        when(customerBalanceRepository.findById(customerBalancesList.getFirst().getId()))
                .thenReturn(Optional.of(customerBalancesList.getFirst()));
        CustomerBalance customerBalance = customerBalanceService.createCustomerBalance(customerBalancesList.getFirst().getId());

        assertEquals(customerBalance.getId(),
                customerBalanceService.findCustomerBalanceById(customerBalance.getId()).getId());
    }

    @Test
    void testCreateCustomerBalanceNotValid() {
        assertThrows(IllegalArgumentException.class, () -> customerBalanceService.createCustomerBalance(null));
    }

    @Test
    void testDeleteCustomerBalanceValid() {
        when(customerBalanceRepository.findById(customerBalancesList.getFirst().getId()))
                .thenReturn(Optional.of(customerBalancesList.getFirst()));

        customerBalanceService.deleteCustomerBalance(customerBalancesList.getFirst().getId());
    }

    @Test
    void testDeleteCustomerBalanceNotValid() {
        assertThrows(NoSuchElementException.class, () -> customerBalanceService.deleteCustomerBalance(-1L));
        assertThrows(IllegalArgumentException.class, () -> customerBalanceService.deleteCustomerBalance(null));
    }
}

package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
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
public class SupermarketBalanceServiceImplTest {
    @InjectMocks
    private SupermarketBalanceServiceImpl supermarketBalanceService;

    @Mock
    private SupermarketBalanceRepository supermarketBalanceRepository;

    @Mock
    private SupermarketBalanceStrategy supermarketBalanceStrategy;

    private List<SupermarketBalance> supermarketBalancesList;

    @BeforeEach
    void setUp() {
        supermarketBalancesList = new ArrayList<>();

        SupermarketBalance supermarketBalance1 = SupermarketBalance.builder()
                .id(1L)
                .supermarketId(1L)
                .amount(BigDecimal.valueOf(100000))
                .build();

        SupermarketBalance supermarketBalance2 = SupermarketBalance.builder()
                .id(2L)
                .supermarketId(2L)
                .amount(BigDecimal.valueOf(50000))
                .build();

        supermarketBalancesList.add(supermarketBalance1);
        supermarketBalancesList.add(supermarketBalance2);
    }

    @Test
    void testCreateSupermarketBalanceValid() {
        when(supermarketBalanceStrategy.createBalance(supermarketBalancesList.getFirst().getSupermarketId()))
                .thenReturn(supermarketBalancesList.getFirst());
        when(supermarketBalanceStrategy.findBalanceById(supermarketBalancesList.getFirst().getId()))
                .thenReturn(supermarketBalancesList.getFirst());

        SupermarketBalance supermarketBalance = supermarketBalanceService.createSupermarketBalance(supermarketBalancesList.get(0).getSupermarketId());

        assertEquals(supermarketBalance.getId(),
                supermarketBalanceService.findSupermarketBalanceById(supermarketBalance.getId()).getId());
    }

    @Test
    void testCreateSupermarketBalanceNotValid() {
        when(supermarketBalanceService.createSupermarketBalance(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> supermarketBalanceService.createSupermarketBalance(null));
    }

    @Test
    void testDeleteSupermarketBalanceValid() {
        supermarketBalanceService.deleteSupermarketBalance(supermarketBalancesList.getFirst().getId());
    }

    @Test
    void testDeleteSupermarketBalanceNotValid() {
        when(supermarketBalanceService.deleteSupermarketBalance(3L)).thenThrow(new NoSuchElementException());
        assertThrows(NoSuchElementException.class, () -> supermarketBalanceService.deleteSupermarketBalance(3L));
        when(supermarketBalanceService.deleteSupermarketBalance(null)).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> supermarketBalanceService.deleteSupermarketBalance(null));
    }
}
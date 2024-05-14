package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
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

    List<SupermarketBalance> supermarketBalancesList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        SupermarketBalance supermarketBalance1 = SupermarketBalance.builder()
                .id(1L)
                .supermarketId(1L)
                .amount(BigDecimal.valueOf(100000))
                .build();

        SupermarketBalance supermarketBalance2 = SupermarketBalance.builder()
                .id(2L)
                .supermarketId(2L)
                .amount(BigDecimal.valueOf(500000))
                .build();

        supermarketBalancesList.add(supermarketBalance1);
        supermarketBalancesList.add(supermarketBalance2);
    }


    @Test
    void testCreateSupermarketBalanceValid() {
        when(supermarketBalanceRepository.save(any())).thenReturn(supermarketBalancesList.getFirst());
        when(supermarketBalanceRepository.findById(supermarketBalancesList.getFirst().getId()))
                .thenReturn(Optional.of(supermarketBalancesList.getFirst()));
        SupermarketBalance supermarketBalance = supermarketBalanceService.createSupermarketBalance(supermarketBalancesList.getFirst().getId());

        assertEquals(supermarketBalance.getId(),
                supermarketBalanceService.findSupermarketBalanceById(supermarketBalance.getId()).getId());
    }

    @Test
    void testCreateSupermarketBalanceNotValid() {
        assertThrows(IllegalArgumentException.class, () -> supermarketBalanceService.createSupermarketBalance(null));
    }

    @Test
    void testDeleteSupermarketBalanceValid() {
        when(supermarketBalanceRepository.findById(supermarketBalancesList.getFirst().getId()))
                .thenReturn(Optional.of(supermarketBalancesList.getFirst()));

        supermarketBalanceService.deleteSupermarketBalance(supermarketBalancesList.getFirst().getId());
    }

    @Test
    void testDeleteSupermarketBalanceNotValid() {
        assertThrows(NoSuchElementException.class, () -> supermarketBalanceService.deleteSupermarketBalance(-1L));
        assertThrows(IllegalArgumentException.class, () -> supermarketBalanceService.deleteSupermarketBalance(null));
    }
}

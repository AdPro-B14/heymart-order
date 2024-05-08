package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceTest {
    CustomerBalance customerBalance;
    SupermarketBalance supermarketBalance;

    @BeforeEach
    void setUp() {
        this.customerBalance = new CustomerBalance();
        this.supermarketBalance = new SupermarketBalance();

        customerBalance.setAmount(BigDecimal.valueOf(1005050));
        supermarketBalance.setAmount(BigDecimal.valueOf(10002003.5));
    }

    @Test
    void testGetAmount() {
        assertEquals(BigDecimal.valueOf(1005050), customerBalance.getAmount());
        assertEquals(BigDecimal.valueOf(10002003.5), supermarketBalance.getAmount());
    }




}

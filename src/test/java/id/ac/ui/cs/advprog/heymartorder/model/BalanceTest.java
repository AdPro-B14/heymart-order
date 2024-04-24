package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceTest {
    CustomerBalance customerBalance;
    SupermarketBalance supermarketBalance;

    @BeforeEach
    void setUp() {
        this.customerBalance = new CustomerBalance(new Customer("Gojo"));
        this.supermarketBalance = new SupermarketBalance(new Supermarket("AdproMart"));

        customerBalance.setAmount(BigDecimal.valueOf(1005050));
        supermarketBalance.setAmount(BigDecimal.valueOf(10002003.5));
    }

    @Test
    void testGetAmount() {
        assertEquals(BigDecimal.valueOf(1005050), customerBalance.getAmount());
        assertEquals(BigDecimal.valueOf(10002003.5), supermarketBalance.getAmount());
    }




}

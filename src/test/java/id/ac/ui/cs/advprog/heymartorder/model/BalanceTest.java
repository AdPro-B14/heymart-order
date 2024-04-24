package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceTest {
    CustomerBalance customerBalance;
    SupermarketBalance supermarketBalance;

    @BeforeEach
    void setUp() {
        CustomerBalance customerBalance = new CustomerBalance(new Customer("Gojo"));
        SupermarketBalance supermarketBalance = new SupermarketBalance(new Supermarket("AdproMart"));

        customerBalance.setAmount(1005050);
        supermarketBalance.setAmount(10002003.5);
    }

    @Test
    void testGetAmount() {
        assertEquals(1005050, customerBalance.getAmount());
        assertEquals(10002003.5, supermarketBalance.getAmount());
    }




}

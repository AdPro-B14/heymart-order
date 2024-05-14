package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;

import java.math.BigDecimal;

public interface CustomerBalanceService {
    CustomerBalance createCustomerBalance(Long customerId);

    void deleteCustomerBalance(Long id);

    CustomerBalance findCustomerBalanceById(Long id);

    CustomerBalance topUp(Long id, BigDecimal amount);

}

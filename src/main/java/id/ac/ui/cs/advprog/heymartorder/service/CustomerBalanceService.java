package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;

import java.math.BigDecimal;

public interface CustomerBalanceService {
    CustomerBalance createCustomerBalance(Long customerId);

    CustomerBalance deleteCustomerBalance(Long id);

    CustomerBalance findCustomerBalanceById(Long id);

    boolean existsCustomerBalanceById(Long id);

    BigDecimal getCustomerBalanceAmountById(Long id);

    CustomerBalance topUp(Long id, BigDecimal amount);

}

package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.Balance;
import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;

import java.math.BigDecimal;

public interface BalanceStrategy <T extends Balance> {

    T createBalance(Long customerId);

    T deleteBalance(Long id);

    T findBalanceById(Long id);

    boolean existsById(Long id);

    BigDecimal getBalanceAmountById(Long id);

    T calculateAtCheckout(Long id, Long amount);
}

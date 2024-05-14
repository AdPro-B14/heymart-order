package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;

import java.math.BigDecimal;

public interface SupermarketBalanceService {
    SupermarketBalance createSupermarketBalance(Long supermarketId);
    SupermarketBalance deleteSupermarketBalance(Long id);
    SupermarketBalance findSupermarketBalanceById(Long id);
    BigDecimal getSupermarketBalanceAmountById(Long id);
    SupermarketBalance withDraw(Long id, BigDecimal amount);
}

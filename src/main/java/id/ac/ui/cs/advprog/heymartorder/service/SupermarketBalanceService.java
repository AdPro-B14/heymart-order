package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;

import java.math.BigDecimal;

public interface SupermarketBalanceService {
    SupermarketBalance createSupermarketBalance(Long supermarketId);
    void deleteSupermarketBalance(Long id);
    SupermarketBalance findSupermarketBalanceById(Long id);
    SupermarketBalance withDraw(Long id, BigDecimal amount);
}

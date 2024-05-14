package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;

import java.math.BigDecimal;

public class SupermarketBalanceServiceImpl implements SupermarketBalanceService {
    
    SupermarketBalanceRepository supermarketBalanceRepository;
    @Override
    public SupermarketBalance createSupermarketBalance(Long supermarketId) {
        if (supermarketId == null) {
            throw new IllegalArgumentException();
        }
        SupermarketBalance supermarketBalance = new SupermarketBalance();
        supermarketBalance.setSupermarketId(supermarketId);
        supermarketBalance.setAmount(BigDecimal.ZERO);

        return supermarketBalanceRepository.save(supermarketBalance);
    }

    @Override
    public void deleteSupermarketBalance(Long id) {
        SupermarketBalance supermarketBalance = findSupermarketBalanceById(id);
        supermarketBalanceRepository.delete(supermarketBalance);
    }

    @Override
    public SupermarketBalance findSupermarketBalanceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return supermarketBalanceRepository.findById(id).orElseThrow();
    }
    @Override
    public SupermarketBalance withDraw(Long id, BigDecimal amount) {
        return null;
    }
}

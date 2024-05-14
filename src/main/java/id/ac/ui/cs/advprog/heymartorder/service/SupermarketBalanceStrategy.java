package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SupermarketBalanceStrategy implements BalanceStrategy<SupermarketBalance> {
    private final SupermarketBalanceRepository supermarketBalanceRepository;
    private final SupermarketBalanceServiceImpl supermarketBalanceService;

    @Autowired
    public SupermarketBalanceStrategy(SupermarketBalanceRepository supermarketBalanceRepository, SupermarketBalanceServiceImpl supermarketBalanceService) {
        this.supermarketBalanceRepository = supermarketBalanceRepository;
        this.supermarketBalanceService = supermarketBalanceService;
    }
    @Override
    public SupermarketBalance createBalance(Long supermarketId) {
        if (supermarketId == null) {
            throw new IllegalArgumentException();
        }
        SupermarketBalance supermarketBalance = new SupermarketBalance();
        supermarketBalance.setSupermarketId(supermarketId);
        supermarketBalance.setAmount(BigDecimal.ZERO);

        return supermarketBalanceRepository.save(supermarketBalance);
    }

    @Override
    public SupermarketBalance deleteBalance(Long id) {
        SupermarketBalance supermarketBalance = findBalanceById(id);
        supermarketBalanceRepository.delete(supermarketBalance);
        return supermarketBalance;
    }

    @Override
    public SupermarketBalance findBalanceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return supermarketBalanceRepository.findById(id).orElseThrow();
    }
    @Override
    public BigDecimal getBalanceAmountById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        SupermarketBalance supermarketBalance = findBalanceById(id);
        return supermarketBalance.getAmount();
    }

    @Override
    public SupermarketBalance calculateAtCheckout(Long supermarketId, Long amount) {
        if (supermarketId == null) {
            throw new IllegalArgumentException();
        }
        SupermarketBalance supermarketBalance = supermarketBalanceService.
                findSupermarketBalanceById(supermarketId);
        BigDecimal currentAmount = supermarketBalance.getAmount();
        BigDecimal checkoutAmount = BigDecimal.valueOf(amount);
        BigDecimal updatedAmount = currentAmount.add(checkoutAmount);
        supermarketBalance.setAmount(updatedAmount);

        return supermarketBalance;
    }
}
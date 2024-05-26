package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.exception.InsufficientBalanceException;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.SupermarketBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SupermarketBalanceServiceImpl implements SupermarketBalanceService {
    private final SupermarketBalanceRepository supermarketBalanceRepository;
    private final BalanceStrategy<SupermarketBalance> balanceStrategy;

    @Autowired
    public SupermarketBalanceServiceImpl(SupermarketBalanceRepository supermarketBalanceRepository, BalanceStrategy<SupermarketBalance> balanceStrategy) {
        this.supermarketBalanceRepository = supermarketBalanceRepository;
        this.balanceStrategy = balanceStrategy;
    }
    @Override
    public SupermarketBalance createSupermarketBalance(Long supermarketId) {
        return balanceStrategy.createBalance(supermarketId);
    }

    @Override
    public SupermarketBalance deleteSupermarketBalance(Long id) {
        return balanceStrategy.deleteBalance(id);
    }

    @Override
    public SupermarketBalance findSupermarketBalanceById(Long id) {
        return balanceStrategy.findBalanceById(id);
    }

    @Override
    public BigDecimal getSupermarketBalanceAmountById(Long id) {
        return balanceStrategy.getBalanceAmountById(id);
    }
    @Override
    public boolean existsSupermarketBalanceById(Long id) {
        return balanceStrategy.existsById(id);
    }
    @Override
    public SupermarketBalance withDraw(Long id, BigDecimal amount) {
        SupermarketBalance supermarketBalance = findSupermarketBalanceById(id);
        BigDecimal currentAmount = supermarketBalance.getAmount();

        if (amount.compareTo(currentAmount) > 0) {
            throw new InsufficientBalanceException();
        }
        BigDecimal newAmount = currentAmount.subtract(amount);
        supermarketBalance.setAmount(newAmount);
        supermarketBalanceRepository.save(supermarketBalance);
        return supermarketBalance;
    }

    public SupermarketBalance calculateAtCheckout(Long id, Long amount) {
        return balanceStrategy.calculateAtCheckout(id, amount);
    }
}

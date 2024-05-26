package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.CustomerBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerBalanceServiceImpl implements CustomerBalanceService {
    private final CustomerBalanceRepository customerBalanceRepository;
    private final BalanceStrategy<CustomerBalance> balanceStrategy;

    @Autowired
    public CustomerBalanceServiceImpl(CustomerBalanceRepository customerBalanceRepository, BalanceStrategy<CustomerBalance> balanceStrategy) {
        this.customerBalanceRepository = customerBalanceRepository;
        this.balanceStrategy = balanceStrategy;
    }
    @Override
    public CustomerBalance createCustomerBalance(Long customerId) {
        return balanceStrategy.createBalance(customerId);
    }

    @Override
    public CustomerBalance deleteCustomerBalance(Long id) {
        return balanceStrategy.deleteBalance(id);
    }

    @Override
    public CustomerBalance findCustomerBalanceById(Long id) {
        return balanceStrategy.findBalanceById(id);
    }

    @Override
    public BigDecimal getCustomerBalanceAmountById(Long id) {
        return balanceStrategy.getBalanceAmountById(id);
    }

    @Override
    public boolean existsCustomerBalanceById(Long id) {
        return balanceStrategy.existsById(id);
    }

    @Override
    public CustomerBalance topUp(Long id, BigDecimal amount) {
        CustomerBalance customerBalance = findCustomerBalanceById(id);
        BigDecimal currentAmount = customerBalance.getAmount();
        BigDecimal newAmount = currentAmount.add(amount);
        customerBalance.setAmount(newAmount);
        customerBalanceRepository.save(customerBalance);
        return customerBalance;
    }

    @Override
    public CustomerBalance calculateAtCheckout(Long customerId, Long amount) {
        return balanceStrategy.calculateAtCheckout(customerId, amount);
    }
}

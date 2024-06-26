package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.exception.InsufficientBalanceException;
import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.CustomerBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomerBalanceStrategy implements BalanceStrategy<CustomerBalance> {
    private final CustomerBalanceRepository customerBalanceRepository;

    @Autowired
    public CustomerBalanceStrategy(CustomerBalanceRepository customerBalanceRepository) {
        this.customerBalanceRepository = customerBalanceRepository;
    }
    @Override
    public CustomerBalance createBalance(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException();
        }
        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setCustomerId(customerId);
        customerBalance.setAmount(BigDecimal.ZERO);

        return customerBalanceRepository.save(customerBalance);
    }

    @Override
    public CustomerBalance deleteBalance(Long id) {
        CustomerBalance customerBalance = findBalanceById(id);
        customerBalanceRepository.delete(customerBalance);
        return customerBalance;
    }

    @Override
    public CustomerBalance findBalanceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return customerBalanceRepository.findByCustomerId(id).orElseThrow();
    }

    @Override
    public boolean existsById(Long id) {
        return customerBalanceRepository.existsById(id);
    }

    @Override
    public BigDecimal getBalanceAmountById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        CustomerBalance customerBalance = findBalanceById(id);
        return customerBalance.getAmount();
    }

    @Override
    public CustomerBalance calculateAtCheckout(Long customerId, Long amount) {
        if (customerId == null) {
            throw new IllegalArgumentException();
        }
        CustomerBalance customerBalance = customerBalanceRepository.
                findById(customerId).orElseThrow();
        BigDecimal currentAmount = customerBalance.getAmount();
        BigDecimal checkoutAmount = BigDecimal.valueOf(amount);
        if (checkoutAmount.compareTo(currentAmount) > 0) {
            throw new InsufficientBalanceException();
        }
        BigDecimal updatedAmount = currentAmount.subtract(checkoutAmount);
        customerBalance.setAmount(updatedAmount);
        customerBalanceRepository.save(customerBalance);
        return customerBalance;
    }
}

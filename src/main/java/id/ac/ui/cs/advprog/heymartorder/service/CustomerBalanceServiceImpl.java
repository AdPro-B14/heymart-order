package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.CustomerBalance;
import id.ac.ui.cs.advprog.heymartorder.repository.CustomerBalanceRepository;

import java.math.BigDecimal;


public class CustomerBalanceServiceImpl implements CustomerBalanceService {

    CustomerBalanceRepository customerBalanceRepository;

    @Override
    public CustomerBalance createCustomerBalance(Long customerId) {
        if (customerId == null) {
            throw new IllegalArgumentException();
        }
        CustomerBalance customerBalance = new CustomerBalance();
        customerBalance.setCustomerId(customerId);
        customerBalance.setAmount(BigDecimal.ZERO);

        return customerBalanceRepository.save(customerBalance);
    }

    @Override
    public void deleteCustomerBalance(Long id) {
        CustomerBalance customerBalance = findCustomerBalanceById(id);
        customerBalanceRepository.delete(customerBalance);
    }

    @Override
    public CustomerBalance findCustomerBalanceById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        return customerBalanceRepository.findById(id).orElseThrow();
    }

    @Override
    public CustomerBalance topUp(Long id, BigDecimal amount) {
        return null;
    }

}

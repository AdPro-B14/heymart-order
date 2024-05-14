package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerBalanceRepository extends JpaRepository<CustomerBalance, Long> {
    Optional<CustomerBalance> findById(Long id);
}

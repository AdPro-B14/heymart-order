package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupermarketBalanceRepository extends JpaRepository<SupermarketBalance, Long> {
    Optional<SupermarketBalance> findById(Long id);
}

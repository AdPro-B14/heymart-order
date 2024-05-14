package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.SupermarketBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupermarketBalanceRepository extends JpaRepository<SupermarketBalance, Long> {
    Optional<SupermarketBalance> findById(Long id);
}

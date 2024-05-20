package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeranjangItemRepository extends JpaRepository<KeranjangItem, String> {
    Optional<KeranjangItem> findByProductId(String productId);
}

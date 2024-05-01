package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeranjangBelanjaRepository extends JpaRepository<KeranjangBelanja, Long> {
    Optional<KeranjangBelanja> findKeranjangBelanjaById(Long id);
}

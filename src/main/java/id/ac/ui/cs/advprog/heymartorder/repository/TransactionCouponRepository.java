package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionCouponRepository extends JpaRepository<TransactionCoupon, String> {
    TransactionCoupon findByCouponId(String id);
    List<TransactionCoupon> findBySupermarketId(Long supermarketId); // findAll in a supermarket

}

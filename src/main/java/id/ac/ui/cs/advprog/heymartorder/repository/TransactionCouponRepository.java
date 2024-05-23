package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCouponRepository extends JpaRepository<TransactionCoupon, String> {
    TransactionCoupon findTransactionCouponByCouponId(String id);
}

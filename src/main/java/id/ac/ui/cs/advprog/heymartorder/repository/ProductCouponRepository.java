package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCouponRepository extends JpaRepository<ProductCoupon, String> {
    ProductCoupon findByCouponId(String id);
}

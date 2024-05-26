package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCouponRepository extends JpaRepository<ProductCoupon, String> {
    ProductCoupon findByCouponId(String id);
    List<ProductCoupon> findBySupermarketId(Long supermarketId);
    List<ProductCoupon> findByProductId(String productId); // findAll in a supermarket


}

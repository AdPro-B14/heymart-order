package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.ProductCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductCouponServiceImpl implements ProductCouponService {
    @Autowired
    ProductCouponRepository productCouponRepository;
    @Override
    public ProductCoupon createProductCoupon(ProductCoupon pCoupon) {
        if (pCoupon == null) {
            throw new IllegalArgumentException();
        } else {
            productCouponRepository.save(pCoupon);
            return pCoupon;
        }
    }

    @Override
    public ProductCoupon findById(String couponId) {
        return productCouponRepository.findByCouponId(couponId);
    }

    @Override
    public List<ProductCoupon> findBySupermarketId(Long supermarketId) {
        return productCouponRepository.findBySupermarketId(supermarketId);
    }

    @Override
    public List<ProductCoupon> findByProductId(String productId) {
        return productCouponRepository.findByProductId(productId);
    }

    @Override
    public List<ProductCoupon> findAll() {
        return productCouponRepository.findAll();
    }

    @Override
    public void delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException();
        } else {
            ProductCoupon pCoupon = findById(id);
            productCouponRepository.delete(pCoupon);
        }
    }
}

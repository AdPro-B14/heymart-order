package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.AddProductCouponRequest;
import id.ac.ui.cs.advprog.heymartorder.dto.SuccessResponse;
import id.ac.ui.cs.advprog.heymartorder.factory.ProductCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.ProductCoupon;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.ProductCouponService;
import id.ac.ui.cs.advprog.heymartorder.service.ProductCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-coupon")
@RequiredArgsConstructor
public class ProductCouponController {

    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final JwtService jwtService;

    private final ProductCouponService productCouponService;


    @GetMapping("/product-coupon/{id}")
    public ResponseEntity<ProductCoupon> getProductCoupon(@PathVariable("id") String couponId) {
        ProductCoupon tcCoupon = productCouponService.findById(couponId);
        return ResponseEntity.ok(tcCoupon);
    }

    @GetMapping("/supermarket-product-coupon/{id}")
    public ResponseEntity<List<ProductCoupon>> getSupermarketProductCoupons(@PathVariable("id") Long supermarketId) {
        List<ProductCoupon> allProductCoupon = productCouponService.findBySupermarketId(supermarketId);
        return ResponseEntity.ok(allProductCoupon);
    }

    @GetMapping("/productid-product-coupon/{id}")
    public ResponseEntity<List<ProductCoupon>> getProductCouponByProductId(@PathVariable("id") String productId) {
        List<ProductCoupon> allProductCoupon = productCouponService.findByProductId(productId);
        return ResponseEntity.ok(allProductCoupon);
    }

    // supermarketId /{id}
    @GetMapping("/all-product-coupon")
    public ResponseEntity<List<ProductCoupon>> getAllProductCoupons() {
        List<ProductCoupon> allProductCoupon = productCouponService.findAll();
        return ResponseEntity.ok(allProductCoupon);
    }


    // supermarketId
    @PostMapping("/create-product-coupon")
    public ResponseEntity<ProductCoupon> addProductCoupon(@RequestHeader(value = "Authorization") String id,
                                                                  @RequestBody AddProductCouponRequest request) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        ProductCouponFactory productCouponFactory = new ProductCouponFactory();
        ProductCoupon tcCoupon = productCouponFactory
                .orderCoupon(request.supermarketId, request.couponName, request.couponNominal, request.productId);

        return ResponseEntity.ok(productCouponService.createProductCoupon(tcCoupon));
    }

    @DeleteMapping("/delete-product-coupon/{id}")
    public ResponseEntity<SuccessResponse> deleteProductCoupon(@RequestHeader(value = "Authorization") String id,
                                                                   @PathVariable("id") String couponId) throws IllegalAccessException {
        String token = id.replace("Bearer ", "");
        if (!jwtService.extractRole(token).equalsIgnoreCase("manager")
                && !jwtService.extractRole(token).equalsIgnoreCase("admin")) {
            throw new IllegalAccessException("You have no access.");
        }

        productCouponService.delete(couponId);
        return ResponseEntity.ok(SuccessResponse.builder().success(true).build());
    }
}

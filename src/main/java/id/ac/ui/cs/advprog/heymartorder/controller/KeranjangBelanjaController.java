package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.KeranjangBelanjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;


@RestController
@RequestMapping("/keranjang")
@RequiredArgsConstructor
public class KeranjangBelanjaController {
    @Value("${spring.route.gateway_url}")
    private String GATEWAY_URL;

    private final JwtService jwtService;
    private final WebClient webClient;

    @Autowired
    private KeranjangBelanjaService keranjangBelanjaService;

//    @GetMapping("get-keranjang/{userId}")
//    public ResponseEntity<GetAllProductOfUserResponse> allProductOfUser(@PathVariable Long userId) {
//        HashMap<String, Integer> allProduct = keranjangBelanjaService.findKeranjangById(userId).getProductMap();
//        GetAllProductOfUserResponse response = GetAllProductOfUserResponse.builder().productMap(allProduct).build();
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/{userId}")
//    public ResponseEntity<KeranjangBelanja> getKeranjang(@PathVariable Long userId) {
//        return ResponseEntity.ok(keranjangBelanjaService.findKeranjangById(userId));
//    }

//    @PostMapping("/create/{userId}")
//    public ResponseEntity<KeranjangBelanja> createKeranjang(@PathVariable Long userId,
//                                                            @RequestBody CreateKeranjangRequest keranjang)) {
//        return ResponseEntity.ok(keranjangBelanjaService.createKeranjangBelanja(userId));
//    }

//    @PostMapping("")
//    public ResponseEntity<KeranjangBelanja> createKeranjang(@RequestHeader (value = "Authorization") String token,
//                                                            @RequestBody CreateKeranjangRequest keranjang) throws IllegalAccessException {
//
//        return ResponseEntity.ok(keranjangBelanjaService.createKeranjangBelanja(keranjang.userId));
//    }

//    @PostMapping("/add-product/{userId}/{productId}")
//    public ResponseEntity<> addProductToKeranjang(@PathVariable Long userId, String productId) {
//        return null;
//    }

//    @PostMapping("/delete-product/{userId}/{productId}")
//    public ResponseEntity<> addProductToKeranjang(@PathVariable Long userId, String productId) {
//        return null;
//    }
}

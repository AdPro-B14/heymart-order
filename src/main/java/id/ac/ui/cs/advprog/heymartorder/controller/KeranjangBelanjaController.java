package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
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

    @PostMapping("/create")
    public ResponseEntity<KeranjangBelanja> createKeranjang(@RequestHeader (value = "Authorization") String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long userId = jwtService.extractUserId(jwt);
        return ResponseEntity.ok(keranjangBelanjaService.createKeranjangBelanja(userId));
    }

    @GetMapping("")
    public ResponseEntity<KeranjangBelanja> getKeranjang(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long userId = jwtService.extractUserId(jwt);

        KeranjangBelanja keranjangBelanja = keranjangBelanjaService.findKeranjangById(userId);
        return ResponseEntity.ok(keranjangBelanja);
    }

    @PostMapping("/add-product")
    public ResponseEntity<KeranjangBelanja> addProductToKeranjang(@RequestHeader(value = "Authorization") String token,
                                                                  @RequestBody AddProductRequest request) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }
        Long userId = jwtService.extractUserId(jwt);
        try {
            KeranjangBelanja keranjangBelanja = keranjangBelanjaService.addProductToKeranjang(userId, request.productId, request.supermarketId);
            return ResponseEntity.ok(keranjangBelanja);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Supermarket Id Mismatch");
        }

    }

    @PostMapping("/remove-product")
    public ResponseEntity<KeranjangBelanja> removeProductFromKeranjang(@RequestHeader(value = "Authorization") String token,
                                                  @RequestBody RemoveProductRequest request) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }

        Long userId = jwtService.extractUserId(jwt);
        KeranjangBelanja keranjangBelanja = keranjangBelanjaService.removeProductFromKeranjang(userId, request.productId);

        return ResponseEntity.ok(keranjangBelanja);
    }

    @PostMapping("/clear")
    public ResponseEntity<KeranjangBelanja> clearKeranjang(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException {
        String jwt = token.replace("Bearer ", "");
        if (!jwtService.extractRole(jwt).equalsIgnoreCase("customer")) {
            throw new IllegalAccessException("You have no access.");
        }

        Long userId = jwtService.extractUserId(jwt);
        KeranjangBelanja keranjangBelanja = keranjangBelanjaService.clearKeranjang(userId);

        return ResponseEntity.ok(keranjangBelanja);
    }
}

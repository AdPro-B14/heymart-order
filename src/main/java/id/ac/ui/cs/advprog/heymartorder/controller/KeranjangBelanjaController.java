package id.ac.ui.cs.advprog.heymartorder.controller;

import id.ac.ui.cs.advprog.heymartorder.dto.*;
import id.ac.ui.cs.advprog.heymartorder.service.JwtService;
import id.ac.ui.cs.advprog.heymartorder.service.KeranjangBelanjaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("{userId}/all-product")
    public ResponseEntity<GetAllProductOfUserResponse> allProductOfUser(@PathVariable Long userId) {
        HashMap<String, Integer> allProduct = keranjangBelanjaService.findKeranjangById(userId).getProductMap();
        GetAllProductOfUserResponse response = GetAllProductOfUserResponse.builder().productMap(allProduct).build();
        return ResponseEntity.ok(response);
    }
}

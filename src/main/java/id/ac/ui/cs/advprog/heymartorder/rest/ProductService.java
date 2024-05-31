package id.ac.ui.cs.advprog.heymartorder.rest;

import id.ac.ui.cs.advprog.heymartorder.dto.GetProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {
    private final WebClient webClient;

    public ProductService(@Value("${spring.route.gateway_url}") String productUrl) {
        this.webClient = WebClient.builder().baseUrl("https://heymart-store-production-qwmmsp4gka-et.a.run.app/product").build();
    }

    public List<GetProductResponse> getAllProduct(Long supermarketId, String token) {
        return webClient.get()
                .uri("/all-product/{supermarketId}", supermarketId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToFlux(GetProductResponse.class)
                .collectList()
                .block();
    }

    public GetProductResponse getProductById(String productId, String token) {
        return webClient.get()
                .uri("/findById/{productId}", productId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .bodyToMono(GetProductResponse.class)
                .block();
    }
}

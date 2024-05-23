package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.dto.GetProductResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {
    private final WebClient webClient;

    public ProductService(@Value("${spring.route.product_url}") String productUrl) {
        this.webClient = WebClient.builder().baseUrl(productUrl + "/api/store/product").build();
    }

    public List<GetProductResponse> getAllProduct(Long supermarketId) {
        return webClient.get()
                .uri("/all-product/{supermarketId}", supermarketId)
                .retrieve()
                .bodyToFlux(GetProductResponse.class)
                .collectList()
                .block();
    }

    public GetProductResponse getProductById(String productId) {
        return webClient.get()
                .uri("/{productId}", productId)
                .retrieve()
                .bodyToMono(GetProductResponse.class)
                .block();
    }
}

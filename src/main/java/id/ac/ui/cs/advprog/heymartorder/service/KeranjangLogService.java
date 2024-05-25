package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangLog;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KeranjangLogService {

    @Autowired
    private KeranjangLogRepository keranjangLogRepository;

    public CompletableFuture<Void> logAction(Long userId, String action, String productId) {
        return CompletableFuture.runAsync(() -> {
            KeranjangLog log = new KeranjangLog(userId, action, productId);
            keranjangLogRepository.save(log);
        });
    }

    public CompletableFuture<Void> logProductAddedToCart(Long userId, String productId) {
        return logAction(userId, "ADD_PRODUCT", productId);
    }

    public CompletableFuture<Void> logProductRemovedFromCart(Long userId, String productId) {
        return logAction(userId, "REMOVE_PRODUCT", productId);
    }

    public CompletableFuture<Void> logCartCleared(Long userId) {
        return logAction(userId, "CLEAR_CART", null);
    }
}

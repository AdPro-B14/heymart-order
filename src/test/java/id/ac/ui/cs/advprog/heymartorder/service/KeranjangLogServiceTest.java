package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangLog;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KeranjangLogServiceTest {

    @Mock
    private KeranjangLogRepository keranjangLogRepository;

    @InjectMocks
    private KeranjangLogService keranjangLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogProductAddedToCart() {
        Long userId = 1L;
        String productId = "product1";

        CompletableFuture<Void> future = keranjangLogService.logProductAddedToCart(userId, productId);
        future.join();  // Ensure the asynchronous operation is completed

        ArgumentCaptor<KeranjangLog> logCaptor = ArgumentCaptor.forClass(KeranjangLog.class);
        verify(keranjangLogRepository, times(1)).save(logCaptor.capture());

        KeranjangLog capturedLog = logCaptor.getValue();
        assertEquals(userId, capturedLog.getUserId());
        assertEquals("ADD_PRODUCT", capturedLog.getAction());
        assertEquals(productId, capturedLog.getProductId());
        assertNotNull(capturedLog.getTimestamp());
    }

    @Test
    void testLogProductRemovedFromCart() {
        Long userId = 1L;
        String productId = "product1";

        CompletableFuture<Void> future = keranjangLogService.logProductRemovedFromCart(userId, productId);
        future.join();  // Ensure the asynchronous operation is completed

        ArgumentCaptor<KeranjangLog> logCaptor = ArgumentCaptor.forClass(KeranjangLog.class);
        verify(keranjangLogRepository, times(1)).save(logCaptor.capture());

        KeranjangLog capturedLog = logCaptor.getValue();
        assertEquals(userId, capturedLog.getUserId());
        assertEquals("REMOVE_PRODUCT", capturedLog.getAction());
        assertEquals(productId, capturedLog.getProductId());
        assertNotNull(capturedLog.getTimestamp());
    }

    @Test
    void testLogCartCleared() {
        Long userId = 1L;

        CompletableFuture<Void> future = keranjangLogService.logCartCleared(userId);
        future.join();  // Ensure the asynchronous operation is completed

        ArgumentCaptor<KeranjangLog> logCaptor = ArgumentCaptor.forClass(KeranjangLog.class);
        verify(keranjangLogRepository, times(1)).save(logCaptor.capture());

        KeranjangLog capturedLog = logCaptor.getValue();
        assertEquals(userId, capturedLog.getUserId());
        assertEquals("CLEAR_CART", capturedLog.getAction());
        assertNull(capturedLog.getProductId());
        assertNotNull(capturedLog.getTimestamp());
    }
}

package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.dto.GetProductResponse;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangItemRepository;
import id.ac.ui.cs.advprog.heymartorder.rest.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KeranjangBelanjaServiceImplTest {

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Mock
    private KeranjangItemRepository keranjangItemRepository;

    @InjectMocks
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Mock
    private ProductService productService;

    @Mock
    private KeranjangLogService logService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(logService.logProductAddedToCart(anyLong(), anyString())).thenReturn(CompletableFuture.completedFuture(null));
    }

    @Test
    void testCreateKeranjangBelanja() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();

        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.createKeranjangBelanja(userId);

        assertEquals(userId, result.getId());
        verify(keranjangBelanjaRepository, times(1)).save(any(KeranjangBelanja.class));
    }

    @Test
    void testFindKeranjangById() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaService.findKeranjangById(userId);

        assertEquals(userId, result.getId());
        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

    @Test
    void testFindKeranjangByIdNotFound() {
        Long userId = 1L;
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> keranjangBelanjaService.findKeranjangById(userId));
    }

    @Test
    void testAddProductToKeranjang() {
        Long userId = 1L;
        String productId1 = "product1";
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(null).build();

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID(productId1);
        product1.setName("Product 1");
        product1.setStock(10);
        product1.setPrice(10L);

        String token = "token";

        when(productService.getProductById(productId1, token)).thenReturn(product1);
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);
        doReturn(CompletableFuture.completedFuture(null)).when(logService).logProductAddedToCart(userId, productId1);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjang(userId, productId1, supermarketId, token);

        assertEquals(1, result.getListKeranjangItem().size());
        assertEquals(productId1, result.getListKeranjangItem().getFirst().getProductId());
        assertEquals(1, result.getListKeranjangItem().getFirst().getAmount());

        result = keranjangBelanjaService.addProductToKeranjang(userId, productId1, supermarketId, token);

        assertEquals(1, result.getListKeranjangItem().size());
        assertEquals(productId1, result.getListKeranjangItem().getFirst().getProductId());
        assertEquals(2, result.getListKeranjangItem().getFirst().getAmount());

        verify(keranjangBelanjaRepository, times(2)).save(keranjangBelanja);
        verify(logService, times(2)).logProductAddedToCart(userId, productId1);
    }

    @Test
    void testAddProductGreaterThanStock() {
        Long userId = 1L;
        String productId1 = "product1";
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(null).build();

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID(productId1);
        product1.setName("Product 1");
        product1.setStock(1);
        product1.setPrice(10L);

        String token = "token";

        when(productService.getProductById(productId1, token)).thenReturn(product1);
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);
        doReturn(CompletableFuture.completedFuture(null)).when(logService).logProductAddedToCart(userId, productId1);

        keranjangBelanjaService.addProductToKeranjang(userId, productId1, supermarketId, token);

        assertThrows(IllegalArgumentException.class, () -> keranjangBelanjaService.addProductToKeranjang(userId, productId1, supermarketId, token));
        verify(logService, times(1)).logProductAddedToCart(userId, productId1);
    }

    @Test
    void testAddProductToKeranjangInsufficientStock() {
        Long userId = 1L;
        String productId = "product1";
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(supermarketId).build();
        keranjangBelanja.setListKeranjangItem(new ArrayList<>());

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID(productId);
        product1.setName("Product 1");
        product1.setStock(0);
        product1.setPrice(10L);

        String token = "token";

        when(productService.getProductById(productId, token)).thenReturn(product1);
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        assertThrows(IllegalArgumentException.class, () -> keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId, token));
    }

    @Test
    void testAddProductToKeranjangProductNotFound() {
        Long userId = 1L;
        String productId = "product1";
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(supermarketId).build();
        keranjangBelanja.setListKeranjangItem(new ArrayList<>());

        String token = "token";

        when(productService.getProductById(productId, token)).thenReturn(null);
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        assertThrows(IllegalArgumentException.class, () -> keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId, token));
    }

    @Test
    void testAddProductToKeranjangSupermarketMismatch() {
        Long userId = 1L;
        String productId = "product1";
        Long supermarketId = 2L;
        Long otherSupermarketId = 3L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(otherSupermarketId).build();
        keranjangBelanja.setListKeranjangItem(new ArrayList<>());

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID(productId);
        product1.setName("Product 1");
        product1.setStock(10);
        product1.setPrice(10L);

        String token = "token";

        when(productService.getProductById(productId, token)).thenReturn(product1);
        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        assertThrows(IllegalArgumentException.class, () -> keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId, token));
    }

    @Test
    void testRemoveProductFromKeranjang() {
        Long userId = 1L;
        String productId = "product1";
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();
        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item = KeranjangItem.getBuilder().setProductId(productId).setAmount(2).build();
        item.setKeranjangbelanja(keranjangBelanja);
        items.add(item);
        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);
        doReturn(CompletableFuture.completedFuture(null)).when(logService).logProductRemovedFromCart(userId, productId);

        KeranjangBelanja result = keranjangBelanjaService.removeProductFromKeranjang(userId, productId);

        assertEquals(1, result.getListKeranjangItem().size());
        assertEquals(1, result.getListKeranjangItem().getFirst().getAmount());
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
        verify(logService, times(1)).logProductRemovedFromCart(userId, productId);
    }

    @Test
    void testRemoveProductFromKeranjangWhenOnlyOneProduct() {
        Long userId = 1L;
        Long supermarketid = 2L;
        String productId = "product1";
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).setSupermarketId(supermarketid).build();

        KeranjangItem item = KeranjangItem.getBuilder()
                .setProductId(productId)
                .setAmount(1)
                .build();
        item.setKeranjangbelanja(keranjangBelanja);

        List<KeranjangItem> items = new ArrayList<>();
        items.add(item);
        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangItemRepository.findByProductId(productId)).thenReturn(Optional.of(item));
        doReturn(CompletableFuture.completedFuture(null)).when(logService).logProductRemovedFromCart(userId, productId);

        keranjangBelanjaService.removeProductFromKeranjang(userId, productId);
        KeranjangBelanja result = keranjangBelanjaRepository.findKeranjangBelanjaById(keranjangBelanja.getId()).orElseThrow();

        assertTrue(result.getListKeranjangItem().isEmpty());
        assertNull(result.getSupermarketId());
        verify(keranjangItemRepository, times(1)).delete(item);
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
        verify(logService, times(1)).logProductRemovedFromCart(userId, productId);
    }

    @Test
    void testClearKeranjang() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();

        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item1 = KeranjangItem.getBuilder().setProductId("product1").setAmount(1).build();
        item1.setKeranjangbelanja(keranjangBelanja);

        KeranjangItem item2 = KeranjangItem.getBuilder().setProductId("product2").setAmount(2).build();
        item2.setKeranjangbelanja(keranjangBelanja);

        items.add(item1);
        items.add(item2);

        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        doReturn(CompletableFuture.completedFuture(null)).when(logService).logCartCleared(userId);

        keranjangBelanjaService.clearKeranjang(userId);

        assertTrue(keranjangBelanja.getListKeranjangItem().isEmpty());
        assertNull(keranjangBelanja.getSupermarketId());
        verify(keranjangItemRepository, times(1)).delete(item1);
        verify(keranjangItemRepository, times(1)).delete(item2);
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
        verify(logService, times(1)).logCartCleared(userId);
    }

    @Test
    void testCountTotal() {
        long userId = 1L;
        String token = "test-token";
        long supermarketId = 123L;

        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item1 = KeranjangItem.getBuilder().setProductId("123").setAmount(2).build();
        items.add(item1);

        KeranjangItem item2 = KeranjangItem.getBuilder().setProductId("456").setAmount(1).build();
        items.add(item2);

        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setSupermarketId(supermarketId).build();
        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID("123");
        product1.setPrice(50L);

        GetProductResponse product2 = new GetProductResponse();
        product2.setUUID("456");
        product2.setPrice(100L);

        when(productService.getProductById("123", token)).thenReturn(product1);
        when(productService.getProductById("456", token)).thenReturn(product2);

        long total = keranjangBelanjaService.countTotal(userId, token);

        assertEquals(200L, total);
    }

}

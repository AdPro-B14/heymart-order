package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangItem;
import id.ac.ui.cs.advprog.heymartorder.dto.GetProductResponse;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateKeranjangBelanja() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();
//        keranjangBelanja.setId(userId);

        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.createKeranjangBelanja(userId);

        assertEquals(userId, result.getId());
        verify(keranjangBelanjaRepository, times(1)).save(any(KeranjangBelanja.class));
    }

    @Test
    void testFindKeranjangById() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder().setId(userId).build();
//        keranjangBelanja.setId(userId);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaService.findKeranjangById(userId);

        assertEquals(userId, result.getId());
        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

    @Test
    void testAddProductToKeranjang() {
        Long userId = 1L;
        String productId = "product1";
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(userId);
        keranjangBelanja.setSupermarketId(supermarketId);
        keranjangBelanja.setListKeranjangItem(new ArrayList<>());

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId);

        assertEquals(1, result.getListKeranjangItem().size());
        assertEquals(productId, result.getListKeranjangItem().get(0).getProductId());
        assertEquals(1, result.getListKeranjangItem().get(0).getAmount());
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
    }

    @Test
    void testRemoveProductFromKeranjang() {
        Long userId = 1L;
        String productId = "product1";
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder().setId(userId).build();
//        keranjangBelanja.setId(userId);
        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item = KeranjangItem.getBuilder().setProductId(productId).setAmount(2).build();
        item.setKeranjangbelanja(keranjangBelanja);
        items.add(item);
        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.removeProductFromKeranjang(userId, productId);

        assertEquals(1, result.getListKeranjangItem().size());
        assertEquals(1, result.getListKeranjangItem().get(0).getAmount());
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
    }

    @Test
    void testRemoveProductFromKeranjangWhenOnlyOneProduct() {
        Long userId = 1L;
        Long supermarketid = 2L;
        String productId = "product1";
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(userId);
        keranjangBelanja.setSupermarketId(2L);

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

        keranjangBelanjaService.removeProductFromKeranjang(userId, productId);
        KeranjangBelanja result = keranjangBelanjaRepository.findKeranjangBelanjaById(keranjangBelanja.getId()).orElseThrow();

        assertTrue(result.getListKeranjangItem().isEmpty());
        assertNull(result.getSupermarketId());
        verify(keranjangItemRepository, times(1)).delete(item);
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
    }
    @Test
    void testClearKeranjang() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(userId);

        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item1 = new KeranjangItem();
        item1.setProductId("product1");
        item1.setAmount(1);
        item1.setKeranjangbelanja(keranjangBelanja);

        KeranjangItem item2 = new KeranjangItem();
        item2.setProductId("product2");
        item2.setAmount(2);
        item2.setKeranjangbelanja(keranjangBelanja);

        items.add(item1);
        items.add(item2);

        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        keranjangBelanjaService.clearKeranjang(userId);

        assertTrue(keranjangBelanja.getListKeranjangItem().isEmpty());
        assertNull(keranjangBelanja.getSupermarketId());
        verify(keranjangItemRepository, times(1)).delete(item1);
        verify(keranjangItemRepository, times(1)).delete(item2);
        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
    }

    @Test
    public void testCountTotal() {
        // Mocking data
        Long userId = 1L;
        Long supermarketId = 1L;

        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .build();

        List<KeranjangItem> items = new ArrayList<>();
        KeranjangItem item1 = KeranjangItem.getBuilder().setProductId("1").setAmount(2).build();
//        item1.setProductId("1");
//        item1.setAmount(2);
        KeranjangItem item2 = KeranjangItem.getBuilder().setProductId("2").setAmount(3).build();
//        item2.setProductId("2");
//        item2.setAmount(3);
        items.add(item1);
        items.add(item2);

        keranjangBelanja.setListKeranjangItem(items);

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        GetProductResponse product1 = new GetProductResponse();
        product1.setUUID("1");
        product1.setName("Product 1");
        product1.setStock(10);
        product1.setPrice(10L);

        GetProductResponse product2 = new GetProductResponse();
        product2.setUUID("2");
        product2.setName("Product 2");
        product2.setStock(20);
        product2.setPrice(20L);
        // Mocking product prices
        // Assume product with ID "1" has price 10 and product with ID "2" has price 20
        when(productService.getProductById("1")).thenReturn(product1);
        when(productService.getProductById("2")).thenReturn(product2);

        // Expected total = (10 * 2) + (20 * 3) = 100
        assertEquals(100L, keranjangBelanjaService.countTotal(userId));
    }

}

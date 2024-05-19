package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KeranjangBelanjaServiceImplTest {

    @InjectMocks
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateKeranjangBelanja() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(null)
                .setProductMap(new HashMap<>())
                .build();

        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(keranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.createKeranjangBelanja(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertNull(result.getSupermarketId());
        assertTrue(result.getProductMap().isEmpty());

        verify(keranjangBelanjaRepository, times(1)).save(any(KeranjangBelanja.class));
    }

    @Test
    void testFindKeranjangById() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaService.findKeranjangById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());

        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

    @Test
    void testClearKeranjang() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(2L)
                .setProductMap(new HashMap<>())
                .build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(keranjangBelanja));

        keranjangBelanjaService.clearKeranjang(userId);

        assertNull(keranjangBelanja.getSupermarketId());
        assertTrue(keranjangBelanja.getProductMap().isEmpty());

        verify(keranjangBelanjaRepository, times(1)).save(keranjangBelanja);
    }

    @Test
    void testAddProductToKeranjangFirstTime() {
        Long userId = 1L;
        Long supermarketId = 2L;
        String productId = "product1";

        KeranjangBelanja existingKeranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .setProductMap(new HashMap<>())
                .build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(existingKeranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(existingKeranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId);

        assertNotNull(result);
        assertEquals(supermarketId, result.getSupermarketId());
        assertTrue(result.getProductMap().containsKey(productId));
        assertEquals(1, result.getProductMap().get(productId));

        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

    @Test
    void testAddProductToKeranjangExistingProduct() {
        Long userId = 1L;
        Long supermarketId = 2L;
        String productId = "product1";

        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put(productId, 1);
        KeranjangBelanja existingKeranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .setProductMap(productMap)
                .build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(existingKeranjangBelanja));
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(existingKeranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjang(userId, productId, supermarketId);

        assertNotNull(result);
        assertEquals(supermarketId, result.getSupermarketId());
        assertTrue(result.getProductMap().containsKey(productId));
        assertEquals(2, result.getProductMap().get(productId));

        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

    @Test
    void testAddProductToKeranjangDifferentSupermarket() {
        Long userId = 1L;
        Long supermarketId = 2L;
        Long differentSupermarketId = 3L;
        String productId = "product1";

        KeranjangBelanja existingKeranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .setProductMap(new HashMap<>())
                .build();

        when(keranjangBelanjaRepository.findKeranjangBelanjaById(userId)).thenReturn(Optional.of(existingKeranjangBelanja));

        assertThrows(IllegalArgumentException.class, () -> {
            keranjangBelanjaService.addProductToKeranjang(userId, productId, differentSupermarketId);
        });

        verify(keranjangBelanjaRepository, times(1)).findKeranjangBelanjaById(userId);
    }

}

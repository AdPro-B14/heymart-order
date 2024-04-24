package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangBelanjaTest {

    private KeranjangBelanja keranjangBelanja;
    private HashMap<String, Integer> productMap;

    @BeforeEach
    void setUp() {
        keranjangBelanja = new KeranjangBelanja("supermarketId", null);
    }
    @Test
    void testProductMap() {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("produk-1", 5);
        productMap.put("produk-2", 3);
        keranjangBelanja.setProductMap(productMap);
        assertEquals(productMap, keranjangBelanja.getProductMap());
    }

    @Test
    void testEmptyProductMap() {
        keranjangBelanja.setProductMap(new HashMap<>());
        assertTrue(keranjangBelanja.getProductMap().isEmpty());
    }

    @Test
    void testNullSupermarketId() {
        assertThrows(IllegalArgumentException.class, () -> {
            keranjangBelanja.setSupermarketId(null);
        });
    }

    @Test
    void testSetNullProductId() {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put(null, 5);
        assertThrows(IllegalArgumentException.class, () -> {
            keranjangBelanja.setProductMap(productMap);
        });
    }
}

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
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("produk-1", 5);
        productMap.put("produk-2", 3);
        keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(1L)
                .setSupermarketId(1L)
                .setProductMap(productMap)
                .build();
    }

    @Test
    void testBuilderValid() {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("produk-1", 5);
        productMap.put("produk-2", 3);
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(1L)
                .setSupermarketId(1L)
                .setProductMap(productMap)
                .build();

        assertEquals(1L, keranjangBelanja.getSupermarketId());
        assertEquals(productMap, keranjangBelanja.getProductMap());
    }

    @Test
    void testNullSupermarketId() {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("produk-1", 5);
        productMap.put("produk-2", 3);

        assertThrows(IllegalArgumentException.class, () -> {
            new KeranjangBelanjaBuilder()
                    .setId(1L)
                    .setSupermarketId(null) // Setting null supermarketId
                    .setProductMap(productMap)
                    .build();
        });
    }

}

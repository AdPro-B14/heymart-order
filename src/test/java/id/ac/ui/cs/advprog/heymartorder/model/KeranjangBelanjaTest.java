package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangBelanjaTest {
    private KeranjangBelanja keranjangBelanja;
    private HashMap<Product, Integer> productMap;

    @BeforeEach
    void setUp() {
        keranjangBelanja = new KeranjangBelanja();
        productMap = new HashMap<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Produk 1");
        product1.setPrice(10000L);
        product1.setStock(50);
        productMap.put(product1, 5);

        Product product2 = new Product();
        product2.setProductId("a8b3f80b-4d9a-4f63-bf10-84ab7fb2ac0a");
        product2.setName("Produk 2");
        product2.setPrice(15000L);
        product2.setStock(30);
        productMap.put(product2, 3);

        keranjangBelanja.setProductMap(productMap);
    }

    @Test
    public void testProductAttributes() {
        Product firstProduct = (Product) productMap.keySet().toArray()[0];

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", firstProduct.getProductId());
        assertEquals("Produk 1", firstProduct.getName());
        assertEquals(10000L, firstProduct.getPrice());
        assertEquals(50, (int) firstProduct.getStock());

        Product secondProduct = (Product) productMap.keySet().toArray()[1];

        assertEquals("a8b3f80b-4d9a-4f63-bf10-84ab7fb2ac0a", secondProduct.getProductId());
        assertEquals("Produk 2", secondProduct.getName());
        assertEquals(15000L, secondProduct.getPrice());
        assertEquals(30, (int) secondProduct.getStock());
    }
}

package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeranjangBelanjaTest {

    @Test
    void testKeranjangBelanjaBuilder() {
        Long userId = 1L;
        Long supermarketId = 2L;

        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .build();

        assertEquals(userId, keranjangBelanja.getId());
        assertEquals(supermarketId, keranjangBelanja.getSupermarketId());
        assertNotNull(keranjangBelanja.getListKeranjangItem());
        assertTrue(keranjangBelanja.getListKeranjangItem().isEmpty());
    }

    @Test
    void testAddAndRemoveKeranjangItem() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(userId);

        String productId1 = "product1";
        Integer amount1 = 5;
        KeranjangItem item1 = KeranjangItem.getBuilder()
                .setProductId(productId1)
                .setAmount(amount1)
                .build();
        item1.setKeranjangbelanja(keranjangBelanja);

        String productId2 = "product2";
        Integer amount2 = 3;
        KeranjangItem item2 = KeranjangItem.getBuilder()
                .setProductId(productId2)
                .setAmount(amount2)
                .build();
        item2.setKeranjangbelanja(keranjangBelanja);

        keranjangBelanja.getListKeranjangItem().add(item1);
        keranjangBelanja.getListKeranjangItem().add(item2);

        List<KeranjangItem> items = keranjangBelanja.getListKeranjangItem();
        assertEquals(2, items.size());
        assertTrue(items.contains(item1));
        assertTrue(items.contains(item2));

        keranjangBelanja.getListKeranjangItem().remove(item1);
        assertEquals(1, items.size());
        assertFalse(items.contains(item1));
        assertTrue(items.contains(item2));
    }
}

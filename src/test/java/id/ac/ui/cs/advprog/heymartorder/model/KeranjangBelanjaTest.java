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
    void testSetId() {
        Long userId = 1L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setId(userId);

        assertEquals(userId, keranjangBelanja.getId());
    }

    @Test
    void testSetSupermarketId() {
        Long supermarketId = 2L;
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        keranjangBelanja.setSupermarketId(supermarketId);

        assertEquals(supermarketId, keranjangBelanja.getSupermarketId());
    }

    @Test
    void testGetAndSetListKeranjangItem() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        List<KeranjangItem> items = List.of(
                KeranjangItem.getBuilder().setProductId("product1").setAmount(1).build(),
                KeranjangItem.getBuilder().setProductId("product2").setAmount(2).build()
        );

        keranjangBelanja.setListKeranjangItem(items);

        assertEquals(items, keranjangBelanja.getListKeranjangItem());
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

    @Test
    void testEqualsAndHashCode() {
        Long userId1 = 1L;
        Long userId2 = 2L;

        KeranjangBelanja keranjangBelanja1 = new KeranjangBelanja();
        keranjangBelanja1.setId(userId1);

        KeranjangBelanja keranjangBelanja2 = new KeranjangBelanja();
        keranjangBelanja2.setId(userId1);

        KeranjangBelanja keranjangBelanja3 = new KeranjangBelanja();
        keranjangBelanja3.setId(userId2);

        assertEquals(keranjangBelanja1, keranjangBelanja2);
        assertNotEquals(keranjangBelanja1, keranjangBelanja3);
        assertEquals(keranjangBelanja1.hashCode(), keranjangBelanja2.hashCode());
        assertNotEquals(keranjangBelanja1.hashCode(), keranjangBelanja3.hashCode());
    }

    @Test
    void testToString() {
        Long userId = 1L;
        Long supermarketId = 2L;

        KeranjangBelanja keranjangBelanja = KeranjangBelanja.getBuilder()
                .setId(userId)
                .setSupermarketId(supermarketId)
                .build();

        String expected = "KeranjangBelanja(id=1, supermarketId=2, listKeranjangItem=[])";
        assertEquals(expected, keranjangBelanja.toString());
    }

}

package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeranjangItemTest {

    @Test
    void testKeranjangItemBuilder() {
        String productId = "product1";
        Integer amount = 5;

        KeranjangItem item = KeranjangItem.getBuilder()
                .setProductId(productId)
                .setAmount(amount)
                .build();

        assertEquals(productId, item.getProductId());
        assertEquals(amount, item.getAmount());
    }

    @Test
    void testSetAndGetProductId() {
        String productId = "product1";
        KeranjangItem item = new KeranjangItem();
        item.setProductId(productId);

        assertEquals(productId, item.getProductId());
    }

    @Test
    void testSetAndGetAmount() {
        Integer amount = 5;
        KeranjangItem item = new KeranjangItem();
        item.setAmount(amount);

        assertEquals(amount, item.getAmount());
    }

    @Test
    void testSetAndGetKeranjangBelanja() {
        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        KeranjangItem item = new KeranjangItem();
        item.setKeranjangbelanja(keranjangBelanja);

        assertEquals(keranjangBelanja, item.getKeranjangbelanja());
    }

    @Test
    void testEqualsAndHashCode() {
        String productId1 = "product1";
        String productId2 = "product2";
        Integer amount1 = 5;
        Integer amount2 = 10;

        KeranjangItem item1 = new KeranjangItem();
        item1.setProductId(productId1);
        item1.setAmount(amount1);

        KeranjangItem item2 = new KeranjangItem();
        item2.setProductId(productId1);
        item2.setAmount(amount1);

        KeranjangItem item3 = new KeranjangItem();
        item3.setProductId(productId2);
        item3.setAmount(amount2);

        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}

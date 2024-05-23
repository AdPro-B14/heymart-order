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
    void testSetAndGetKeranjangBelanja() {
        String productId = "product1";
        Integer amount = 5;

        KeranjangItem item = new KeranjangItem();
        item.setProductId(productId);
        item.setAmount(amount);

        KeranjangBelanja keranjangBelanja = new KeranjangBelanja();
        item.setKeranjangbelanja(keranjangBelanja);

        assertEquals(productId, item.getProductId());
        assertEquals(amount, item.getAmount());
        assertEquals(keranjangBelanja, item.getKeranjangbelanja());
    }
}

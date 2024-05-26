package id.ac.ui.cs.advprog.heymartorder.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class KeranjangLogTest {

    @Test
    void testKeranjangLogConstructorAndGetters() {
        Long userId = 1L;
        String action = "ADD";
        String productId = "product1";
        LocalDateTime beforeCreation = LocalDateTime.now();

        KeranjangLog log = new KeranjangLog(userId, action, productId);

        assertEquals(userId, log.getUserId());
        assertEquals(action, log.getAction());
        assertEquals(productId, log.getProductId());
        assertNotNull(log.getTimestamp());
        assertTrue(log.getTimestamp().isAfter(beforeCreation) || log.getTimestamp().isEqual(beforeCreation));
    }

    @Test
    void testKeranjangLogDefaultConstructor() {
        KeranjangLog log = new KeranjangLog();

        assertNull(log.getId());
        assertNull(log.getUserId());
        assertNull(log.getAction());
        assertNull(log.getProductId());
        assertNull(log.getTimestamp());
    }

    @Test
    void testKeranjangLogSetters() {
        KeranjangLog log = new KeranjangLog();
        Long id = 1L;
        Long userId = 2L;
        String action = "REMOVE";
        String productId = "product2";
        LocalDateTime timestamp = LocalDateTime.now();

        log.setId(id);
        log.setUserId(userId);
        log.setAction(action);
        log.setProductId(productId);
        log.setTimestamp(timestamp);

        assertEquals(id, log.getId());
        assertEquals(userId, log.getUserId());
        assertEquals(action, log.getAction());
        assertEquals(productId, log.getProductId());
        assertEquals(timestamp, log.getTimestamp());
    }

    @Test
    void testHashCode() {
        KeranjangLog log1 = new KeranjangLog(1L, "ADD", "product1");
        KeranjangLog log2 = new KeranjangLog(1L, "ADD", "product1");

        assertEquals(log1.hashCode(), log2.hashCode());

        HashSet<KeranjangLog> set = new HashSet<>();
        set.add(log1);
        set.add(log2);

        assertEquals(1, set.size());
    }

    @Test
    void testToString() {
        KeranjangLog log = new KeranjangLog(1L, "ADD", "product1");
        String expected = "KeranjangLog(id=null, userId=1, action=ADD, productId=product1, timestamp=" + log.getTimestamp() + ")";

        assertEquals(expected, log.toString());
    }
}

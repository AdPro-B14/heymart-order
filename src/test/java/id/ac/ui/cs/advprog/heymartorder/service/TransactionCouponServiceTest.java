package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.repository.TransactionCouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionCouponServiceTest {
    @InjectMocks
    TransactionCouponServiceImpl transactionCouponService;
    @Mock
    TransactionCouponRepository transactionCouponRepository;
    List<TransactionCoupon> tcCoupons;

    @BeforeEach
    void setUp() {
        tcCoupons = new ArrayList<>();

        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();

        TransactionCoupon transactionCoupon1 = transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd6", "Kupon 4.4.24",
                        10000L, false, 50000L);

        TransactionCoupon transactionCoupon2 = transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd7", "Kupon Ramadhan Sale",
                        50000L, false, 100000L);

        tcCoupons.add(transactionCoupon1);
        tcCoupons.add(transactionCoupon2);
    }

    @Test
    void testCreateTransactionCoupon() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(transactionCouponRepository).save(tcCoupon);

        TransactionCoupon result = transactionCouponService.createTransactionCoupon(tcCoupon);
        verify(transactionCouponRepository, times(1)).save(tcCoupon);
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
    }

    @Test
    void testCreateTransactionCouponIfAlreadyExists() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(transactionCouponRepository).findById(tcCoupon.getCouponId());

        assertNull(transactionCouponService.createTransactionCoupon(tcCoupon));
        verify(transactionCouponRepository, times(0)).save(tcCoupon);

    }

    @Test
    void testUpdateIsUsed() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();

        TransactionCouponFactory transactionCouponFactory = new TransactionCouponFactory();
        TransactionCoupon newTcCoupon = transactionCouponFactory
                .orderCoupon("eb558e9f-1c39-460e-8860-71af6af63bd6", "Kupon 4.4.24",
                        10000L, false, 50000L);
        doReturn(tcCoupon).when(transactionCouponRepository).findById(tcCoupon.getCouponId());
        doReturn(newTcCoupon).when(transactionCouponRepository).save(any(TransactionCoupon.class));

        TransactionCoupon result = transactionCouponService.updateIsUsed(tcCoupon.getCouponId(), true);

        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
        assertTrue(result.isUsed());
        verify(transactionCouponRepository, times(1)).save(any(TransactionCoupon.class));
    }

    @Test
    void testUpdateIsUsedInvalidCouponId() {
        doReturn(null).when(transactionCouponRepository).findById("foobar");

        assertThrows(NoSuchElementException.class,
                () -> transactionCouponService.updateIsUsed("foobar", true));

        verify(transactionCouponRepository, times(0)).save(any(TransactionCoupon.class));
    }

    @Test
    void testFindIdIfIdFound() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(transactionCouponRepository).findById(tcCoupon.getCouponId());

        TransactionCoupon result = transactionCouponService.findById(tcCoupon.getCouponId());
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        doReturn(null).when(transactionCouponRepository).findById("zczc");
        assertNull(transactionCouponRepository.findById("zczc"));
    }

    @Test
    void testFindAll() {
        TransactionCoupon tcCoupon = tcCoupons.get(1);
        doReturn(tcCoupons).when(transactionCouponRepository).findAll();

        List<TransactionCoupon> results = transactionCouponService.findAll();
        for (int i = 0; i< results.size(); i++) {
            assertEquals(tcCoupons.get(i).getCouponId(), results.get(i).getCouponId());
        }
        assertEquals(2, results.size());
    }
}

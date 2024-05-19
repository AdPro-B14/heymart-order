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
import java.util.Optional;

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
                .orderCoupon(1L, "Kupon 4.4.24",
                        10000L, 50000L);

        TransactionCoupon transactionCoupon2 = transactionCouponFactory
                .orderCoupon(1L, "Kupon Ramadhan Sale",
                        50000L, 100000L);

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
    void testCreateTransactionCouponNotValid() {
        assertThrows(IllegalArgumentException.class, () -> transactionCouponService.createTransactionCoupon(null));
    }


    @Test
    void testFindIdIfIdFound() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        doReturn(tcCoupon).when(transactionCouponRepository).findByCouponId(tcCoupon.getCouponId());

        TransactionCoupon result = transactionCouponService.findById(tcCoupon.getCouponId());
        assertEquals(tcCoupon.getCouponId(), result.getCouponId());
    }

    @Test
    void testFindIdIfIdNotFound() {
        doReturn(null).when(transactionCouponRepository).findByCouponId("zczc");
        assertNull(transactionCouponRepository.findByCouponId("zczc"));
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
    
    @Test
    void deleteTransactionCouponInvalid() {
        assertThrows(IllegalArgumentException.class, () -> transactionCouponService.delete(null));
    }
}

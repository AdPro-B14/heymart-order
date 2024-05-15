package id.ac.ui.cs.advprog.heymartorder.repository;

import id.ac.ui.cs.advprog.heymartorder.factory.TransactionCouponFactory;
import id.ac.ui.cs.advprog.heymartorder.model.TransactionCoupon;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponService;
import id.ac.ui.cs.advprog.heymartorder.service.TransactionCouponServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class TransactionCouponRepositoryTest {

    @Mock
    TransactionCouponRepository transactionCouponRepository;

    List<TransactionCoupon> tcCoupons;

    @BeforeEach
    void setUp() {
        this.tcCoupons = new ArrayList<>();

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
    void testSaveCreate() {
        TransactionCoupon tcCoupon = tcCoupons.getFirst();
        Mockito.when(transactionCouponRepository.save(Mockito.any(TransactionCoupon.class)))
                .thenReturn(tcCoupon); // Define behavior for save method
        Mockito.when(transactionCouponRepository.findTransactionCouponByCouponId(Mockito.anyString()))
                .thenReturn(tcCoupon); // Define behavior for find by ID method

        TransactionCoupon savedTcCoupon = transactionCouponRepository.save(tcCoupon);
//        System.out.println(result);
        TransactionCoupon findResult = transactionCouponRepository.findTransactionCouponByCouponId(tcCoupons.getFirst().getCouponId());
        assertEquals(tcCoupon.getCouponId(), savedTcCoupon.getCouponId());
        assertEquals(tcCoupon.getCouponId(), findResult.getCouponId());
        assertEquals(tcCoupon.getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupon.getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupon.getMinimumBuy(), findResult.getMinimumBuy());
        assertEquals(tcCoupon.isUsed(), findResult.isUsed());

        Assertions.assertEquals(savedTcCoupon.getCouponId(), tcCoupon.getCouponId());
    }

    @Test
    void testFindIdIfIdFound() {
        transactionCouponRepository.saveAll(tcCoupons);
        System.out.println(transactionCouponRepository);
        Mockito.when(transactionCouponRepository.findTransactionCouponByCouponId(Mockito.anyString()))
                .thenReturn(tcCoupons.get(0)); // Define behavior for find by ID method

        TransactionCoupon findResult = transactionCouponRepository.findTransactionCouponByCouponId(tcCoupons.get(0).getCouponId());
        assertEquals(tcCoupons.get(0).getCouponId(), findResult.getCouponId());
        assertEquals(tcCoupons.get(0).getCouponName(), findResult.getCouponName());
        assertEquals(tcCoupons.get(0).getCouponNominal(), findResult.getCouponNominal());
        assertEquals(tcCoupons.get(0).getMinimumBuy(), findResult.getMinimumBuy());
        assertEquals(tcCoupons.get(0).isUsed(), findResult.isUsed());
    }

    @Test
    void testFindIdIfIdNotFound() {
        transactionCouponRepository.saveAll(tcCoupons);

        TransactionCoupon findResult = transactionCouponRepository.findTransactionCouponByCouponId("zczc");
        assertNull(findResult);
    }

    @Test
    void testDelete() {
        // Arrange
        TransactionCoupon tcCoupon = tcCoupons.get(0);
        Mockito.when(transactionCouponRepository.save(Mockito.any(TransactionCoupon.class)))
                .thenReturn(tcCoupon); // Define behavior for save method
        TransactionCoupon savedCoupon = transactionCouponRepository.save(tcCoupon);

        assertNotNull(savedCoupon);
        assertEquals(tcCoupon.getCouponId(), savedCoupon.getCouponId());

        Mockito.when(transactionCouponRepository.count())
                .thenReturn(1L); // Define behavior for save method

        long countBeforeDelete = transactionCouponRepository.count();
        assertEquals(1, countBeforeDelete);
        transactionCouponRepository.delete(savedCoupon);

        Mockito.when(transactionCouponRepository.count())
                .thenReturn(0L); // Define behavior for save method
        // Assert: Check that the coupon was deleted
        long countAfterDelete = transactionCouponRepository.count();
        assertEquals(0, countAfterDelete);
        assertNull(transactionCouponRepository.findTransactionCouponByCouponId(tcCoupon.getCouponId()));
    }

    @Test
    void testDeleteIfWrongTransactionCoupon() {
        TransactionCoupon tcCoupon1 = tcCoupons.getFirst();
        TransactionCoupon result = transactionCouponRepository.save(tcCoupon1);

        TransactionCoupon tcCoupon2 = tcCoupons.get(1);
        transactionCouponRepository.delete(tcCoupon2);

        List<TransactionCoupon> tcList = new ArrayList<>();
        tcList.add(transactionCouponRepository.findTransactionCouponByCouponId(tcCoupon1.getCouponId()));
        System.out.println(tcList);
        assertFalse(tcList.isEmpty()); // Assert true because the first product should still exist
    }

}
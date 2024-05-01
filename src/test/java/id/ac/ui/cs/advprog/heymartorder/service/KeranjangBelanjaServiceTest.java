package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaServiceTest {

    @InjectMocks
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @Test
    void testFindKeranjangBelanjaById() {
        KeranjangBelanja someKeranjangBelanja = new KeranjangBelanja();
        when(keranjangBelanjaRepository.findById(any(Long.class))).thenReturn(Optional.of(someKeranjangBelanja));

        KeranjangBelanja result = keranjangBelanjaService.findKeranjangBelanjaById(1L);

        assertEquals(someKeranjangBelanja, result);
    }

    @Test
    void testClearKeranjang() {
        keranjangBelanjaService.clearKeranjang();

        verify(keranjangBelanjaRepository, times(1)).deleteAll();
    }

    @Test
    void testAddProductToKeranjang() {
        KeranjangBelanja someKeranjangBelanja = new KeranjangBelanja();
        when(keranjangBelanjaRepository.save(any(KeranjangBelanja.class))).thenReturn(someKeranjangBelanja);

        KeranjangBelanja result = keranjangBelanjaService.addProductToKeranjang("productId");

        assertEquals(someKeranjangBelanja, result);
    }
}

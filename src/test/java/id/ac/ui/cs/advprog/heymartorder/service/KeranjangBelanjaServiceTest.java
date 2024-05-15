package id.ac.ui.cs.advprog.heymartorder.service;

import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanja;
import id.ac.ui.cs.advprog.heymartorder.model.KeranjangBelanjaBuilder;
import id.ac.ui.cs.advprog.heymartorder.repository.KeranjangBelanjaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class KeranjangBelanjaServiceTest {

    @InjectMocks
    private KeranjangBelanjaServiceImpl keranjangBelanjaService;

    @Mock
    private KeranjangBelanjaRepository keranjangBelanjaRepository;

    @BeforeEach
    void setUp() {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("produk-1", 5);
        productMap.put("produk-2", 3);
        KeranjangBelanja keranjangBelanja = new KeranjangBelanjaBuilder()
                .setId(1L)
                .setSupermarketId(1L)
                .setProductMap(productMap)
                .build();

        when(keranjangBelanjaRepository.findById(keranjangBelanja.getId())).thenReturn(Optional.of(keranjangBelanja));
    }

//    @Test
//    void testAddProductToKeranjang() {
//        keranjangBelanjaService.addProductToKeranjang("produk-3");
//
//        assertEquals(3, keranjangBelanjaService.findKeranjangBelanjaById(1L).getProductMap().size());
//    }
}

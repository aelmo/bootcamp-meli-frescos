package com.mercadolibre.bootcampmelifrescos.unit;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderStatusDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import com.mercadolibre.bootcampmelifrescos.service.impl.PurchaseOrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.*;

@SpringBootTest

public class PurchaseOrderServiceTest {
    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseStatusesRepository purchaseStatusesRepository;

    @Mock
    private BatchRepository batchRepository;

    @InjectMocks
    private PurchaseOrderServiceImpl purchaseOrderService;

    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    private Batch batchSampleProduct = new Batch();
    private Product sampleProduct = new Product();
    private Product sampleProduct2 = new Product();
    private Product sampleProduct3 = new Product();
    private Cart sampleCart = new Cart();
    private Seller sampleSeller = new Seller();
    private Set<Product> sampleProducts = new HashSet<>();
    private InboundOrder inboundOrder = new InboundOrder();
    private Buyer sampleBuyer = new Buyer();
    private PurchaseStatuses status = new PurchaseStatuses();
    private PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
    private PurchaseRequestProductsDTO purchaseRequestProductsDTO = new PurchaseRequestProductsDTO();

    @BeforeEach
    public void init() {
        //create sample buyer
        sampleBuyer.setName("Sample buyer");
        sampleBuyer.setEmail("sample.buyer@email.com");
        sampleBuyer.setPassword("123456");
        sampleBuyer.setId(1L);

        //create sample seller
        sampleSeller.setId(1L);
        sampleSeller.setName("Sample Seller");
        sampleSeller.setEmail("sample.selle@email.com");
        sampleSeller.setPassword("123456");

        //create sample product
        sampleProduct.setId(1L);
        sampleProduct.setName("Sample product");
        sampleProduct.setSeller(sampleSeller);
        sampleProduct.setAmount(100.0F);

        sampleProduct2.setId(2L);
        sampleProduct2.setName("Sample product");
        sampleProduct2.setSeller(sampleSeller);
        sampleProduct2.setAmount(200.0F);

        sampleProduct3.setId(3L);
        sampleProduct3.setName("Sample product");
        sampleProduct3.setSeller(sampleSeller);
        sampleProduct3.setAmount(300.0F);

        //create relations between seller and product
        sampleProducts.add(sampleProduct);
        sampleProducts.add(sampleProduct2);
        sampleProducts.add(sampleProduct3);
        sampleSeller.setProducts(sampleProducts);

        //create batch
        batchSampleProduct.setProduct(sampleProduct);
        batchSampleProduct.setId(1L);
        batchSampleProduct.setInboundOrder(inboundOrder);
        batchSampleProduct.setCurrentQuantity(50);
        batchSampleProduct.setCurrentTemperature(10.0F);
        batchSampleProduct.setDueDate(LocalDate.of(2021, 01, 01));
        batchSampleProduct.setInitialQuantity(51);
        batchSampleProduct.setMinimumTemperature(10.0F);
        batchSampleProduct.setManufacturingDate(LocalDate.of(2021,01,10));
        batchSampleProduct.setManufacturingTime(LocalDateTime.of(2021, 01, 10, 01,00));

        status.setId(1L);
        status.setTitle("Paid");

        sampleCart.setId(1L);
        sampleCart.setProducts(sampleProducts);

        purchaseRequestProductsDTO.setProductId(1L);
        purchaseRequestProductsDTO.setQuantity(3);

        Set<PurchaseRequestProductsDTO> sampleSetPurchaseRequestProductsDTO = new HashSet<>();
        sampleSetPurchaseRequestProductsDTO.add(purchaseRequestProductsDTO);

        purchaseOrderDTO.setBuyerId(sampleBuyer.getId());
        purchaseOrderDTO.setDate(LocalDate.now());
        purchaseOrderDTO.setProducts(sampleSetPurchaseRequestProductsDTO);
        purchaseOrderDTO.setOrderStatus(new PurchaseOrderStatusDTO(1L));

        purchaseOrder = new PurchaseOrder(1L, LocalDate.now(), status, sampleCart, sampleBuyer);

        when(productRepository.getOne(notNull())).thenReturn(sampleProduct);
        when(purchaseStatusesRepository.getOne(notNull())).thenReturn(status);
        when(batchRepository.save(notNull())).thenReturn(batchSampleProduct);
        when(batchRepository.getBatchByProductId(notNull())).thenReturn(batchSampleProduct);

    }

    @Test
    public void shouldReturnAPurchaseOrderTotalPrice() throws Exception {
        PurchaseAmountDTO expectedResult = new PurchaseAmountDTO(600.0);
        when(purchaseOrderRepository.save(notNull())).thenReturn(expectedResult);
        PurchaseAmountDTO result = purchaseOrderService.createPurchaseOrder(purchaseOrderDTO);
        assertThat(result).isSameAs(expectedResult);
    }
}

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

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest

public class PurchaseOrderServiceTest {
    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseStatusesRepository purchaseStatusesRepository;
    @Mock
    private PurchaseProductsRepository purchaseProductsRepository;
    @Mock
    private BatchRepository batchRepository;
    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private PurchaseOrderServiceImpl purchaseOrderService;

    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    private PurchaseOrder purchaseOrder2 = new PurchaseOrder();
    private Batch batchSampleProduct = new Batch();
    private Product sampleProduct = new Product();
    private Product sampleProduct2 = new Product();
    private Product sampleProduct3 = new Product();
    private Seller sampleSeller = new Seller();
    private Set<Product> sampleProducts = new HashSet<>();
    private InboundOrder inboundOrder = new InboundOrder();
    private Buyer sampleBuyer = new Buyer();
    private PurchaseStatuses status = new PurchaseStatuses();
    private PurchaseStatuses status2 = new PurchaseStatuses();
    private PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
    private PurchaseOrderDTO purchaseOrderDTO2 = new PurchaseOrderDTO();
    private PurchaseRequestProductsDTO purchaseRequestProductsDTO = new PurchaseRequestProductsDTO();
    private PurchaseRequestProductsDTO purchaseRequestProductsDTO2 = new PurchaseRequestProductsDTO();
    private PurchaseOrderProducts samplePurchaseProduct = new PurchaseOrderProducts();
    private PurchaseOrderProducts samplePurchaseProduct2 = new PurchaseOrderProducts();

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
        batchSampleProduct.setLastQuantity(50);
        batchSampleProduct.setCurrentTemperature(10.0F);
        batchSampleProduct.setDueDate(LocalDate.of(2021, 01, 01));
        batchSampleProduct.setInitialQuantity(51);
        batchSampleProduct.setMinimumTemperature(10.0F);
        batchSampleProduct.setManufacturingDate(LocalDate.of(2021,01,10));
        batchSampleProduct.setManufacturingTime(LocalDateTime.of(2021, 01, 10, 01,00));

        status.setId(1L);
        status.setTitle("Paid");

        status2.setId(2L);
        status2.setTitle("In process");

        purchaseRequestProductsDTO.setProductId(1L);
        purchaseRequestProductsDTO.setQuantity(3);

        purchaseRequestProductsDTO2.setProductId(1L);
        purchaseRequestProductsDTO2.setQuantity(4);

        Set<PurchaseRequestProductsDTO> sampleSetPurchaseRequestProductsDTO = new HashSet<>();
        Set<PurchaseRequestProductsDTO> sampleSetPurchaseRequestProductsDTO2 = new HashSet<>();
        sampleSetPurchaseRequestProductsDTO.add(purchaseRequestProductsDTO);
        sampleSetPurchaseRequestProductsDTO2.add(purchaseRequestProductsDTO2);
        Set<PurchaseOrderProducts> samplePurchaseOrderProductsSet = new HashSet<>();
        Set<PurchaseOrderProducts> samplePurchaseOrderProductsSet2 = new HashSet<>();
        samplePurchaseOrderProductsSet.add(samplePurchaseProduct);
        samplePurchaseOrderProductsSet2.add(new PurchaseOrderProducts(1L, sampleProduct, purchaseOrder, 1, 1L));

        purchaseOrderDTO.setBuyerId(sampleBuyer.getId());
        purchaseOrderDTO.setDate(LocalDate.now());
        purchaseOrderDTO.setProducts(sampleSetPurchaseRequestProductsDTO);
        purchaseOrderDTO.setOrderStatus(new PurchaseOrderStatusDTO(1L));

        purchaseOrderDTO2.setBuyerId(sampleBuyer.getId());
        purchaseOrderDTO2.setDate(LocalDate.of(2021,06,28));
        purchaseOrderDTO2.setProducts(sampleSetPurchaseRequestProductsDTO2);
        purchaseOrderDTO2.setOrderStatus(new PurchaseOrderStatusDTO(2L));

        purchaseOrder = new PurchaseOrder(1L, LocalDate.now(), status, samplePurchaseOrderProductsSet, sampleBuyer);
        purchaseOrder2 = new PurchaseOrder(1L, LocalDate.of(2021,06,28), status2, samplePurchaseOrderProductsSet2, sampleBuyer);

        when(productRepository.getOne(notNull())).thenReturn(sampleProduct);
        when(purchaseStatusesRepository.getOne(notNull())).thenReturn(status);
        when(batchRepository.save(notNull())).thenReturn(batchSampleProduct);
        when(batchRepository.getBatchByProductId(notNull())).thenReturn(batchSampleProduct);
        when(purchaseOrderRepository.findById(notNull())).thenReturn(java.util.Optional.ofNullable(purchaseOrder));
        when(purchaseProductsRepository.findAllByPurchaseOrder(notNull())).thenReturn(new ArrayList<>(samplePurchaseOrderProductsSet2));
        when(purchaseOrderRepository.save(notNull())).thenReturn(purchaseOrder2);
        when(buyerRepository.getOne(notNull())).thenReturn(sampleBuyer);
        when(batchRepository.findById(notNull())).thenReturn(Optional.ofNullable(batchSampleProduct));
        doNothing().when(purchaseProductsRepository).deleteAll(notNull());
    }

    @Test
    public void shouldReturnAPurchaseOrderTotalPrice() throws Exception {
        PurchaseAmountDTO expectedResult = new PurchaseAmountDTO(600.0);
        PurchaseAmountDTO result = purchaseOrderService.getAmountOfAnPurchaseOrder(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldUpdateAPurchaseOrder() throws Exception {
        PurchaseAmountDTO expectedResult = new PurchaseAmountDTO(400.0);
        PurchaseAmountDTO result = purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO2, 1L);
        assertThat(result).isEqualTo(expectedResult);
    }
}

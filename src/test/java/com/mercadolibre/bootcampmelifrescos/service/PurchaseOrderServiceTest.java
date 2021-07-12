package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderStatusRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountResponse;
import com.mercadolibre.bootcampmelifrescos.dtos.request.GetDashBoardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderStatusDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseRequestProductsDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.DashboardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.MostSoldProduct;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.*;
import com.mercadolibre.bootcampmelifrescos.service.impl.PurchaseOrderServiceImpl;

import com.mercadolibre.bootcampmelifrescos.service.impl.ValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    private ValidatorImpl validator;

    @InjectMocks
    private PurchaseOrderServiceImpl purchaseOrderService;

    private PurchaseOrder purchaseOrder = new PurchaseOrder();
    private PurchaseOrder purchaseOrderToUpdate = new PurchaseOrder();
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
    private PurchaseOrderRequest purchaseOrderRequest = new PurchaseOrderRequest();
    private PurchaseOrderRequest purchaseOrderRequestToUpdate = new PurchaseOrderRequest();
    private PurchaseRequestProductsRequest purchaseRequestProductsRequest = new PurchaseRequestProductsRequest();
    private PurchaseRequestProductsRequest purchaseRequestProductsRequestToUpdate = new PurchaseRequestProductsRequest();
    private final Set<PurchaseRequestProductsRequest> sampleSetPurchaseRequestProductsRequest = new HashSet<>();
    private final Set<PurchaseRequestProductsRequest> sampleSetPurchaseRequestProductsRequestToUpdate = new HashSet<>();
    private final Set<PurchaseOrderProducts> samplePurchaseOrderProductsSet = new HashSet<>();
    private final Set<PurchaseOrderProducts> samplePurchaseOrderProductsSetToUpdate = new HashSet<>();

    @BeforeEach
    public void init() {
        //create sample buyer
        sampleBuyer.setName("Sample buyer");
        sampleBuyer.setEmail("");
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

        purchaseRequestProductsRequest.setProductId(1L);
        purchaseRequestProductsRequest.setQuantity(3);

        sampleSetPurchaseRequestProductsRequest.add(new PurchaseRequestProductsRequest(1L, 3));

        samplePurchaseOrderProductsSet.add(new PurchaseOrderProducts(1L, sampleProduct, purchaseOrder, 3, 1L));

        purchaseOrderRequest.setBuyerId(sampleBuyer.getId());
        purchaseOrderRequest.setDate(LocalDate.now());
        purchaseOrderRequest.setProducts(sampleSetPurchaseRequestProductsRequest);
        purchaseOrderRequest.setOrderStatus(new PurchaseOrderStatusRequest(1L));

        purchaseOrder = new PurchaseOrder(1L, LocalDate.now(), status, samplePurchaseOrderProductsSet, sampleBuyer);

        this.samplePurchaseOrderProductsSetToUpdate.add(new PurchaseOrderProducts(1L, sampleProduct, purchaseOrder, 4, 1L));
        this.sampleSetPurchaseRequestProductsRequestToUpdate.add(new PurchaseRequestProductsRequest(1L, 4));

        this.purchaseOrderRequestToUpdate.setBuyerId(sampleBuyer.getId());
        this.purchaseOrderRequestToUpdate.setDate(LocalDate.of(2021,06,28));
        this.purchaseOrderRequestToUpdate.setProducts(sampleSetPurchaseRequestProductsRequestToUpdate);
        this.purchaseOrderRequestToUpdate.setOrderStatus(new PurchaseOrderStatusRequest(2L));

        this.purchaseOrderToUpdate = new PurchaseOrder(1L, LocalDate.of(2021,04,28), status, samplePurchaseOrderProductsSetToUpdate, sampleBuyer);

        when(productRepository.findById(notNull())).thenReturn(Optional.ofNullable(sampleProduct));
        when(purchaseStatusesRepository.findById(notNull())).thenReturn(Optional.ofNullable(status));

        when(batchRepository.save(notNull())).thenReturn(batchSampleProduct);
        when(batchRepository.getBatchByProductId(notNull())).thenReturn(batchSampleProduct);
        when(batchRepository.findById(notNull())).thenReturn(Optional.ofNullable(batchSampleProduct));

        when(purchaseOrderRepository.findById(notNull())).thenReturn(Optional.ofNullable(purchaseOrder));
        when(purchaseOrderRepository.save(notNull())).thenReturn(purchaseOrder);

        doNothing().when(purchaseProductsRepository).deleteAll(notNull());

        when(buyerRepository.findById(notNull())).thenReturn(Optional.ofNullable(sampleBuyer));

    }


    @Test
    public void shouldReturnAPurchaseOrderTotalPrice() throws Exception {
        PurchaseAmountResponse expectedResult = new PurchaseAmountResponse(300.0);
        when(purchaseProductsRepository.findAllByPurchaseOrder(notNull())).thenReturn(new ArrayList<>(samplePurchaseOrderProductsSet));
        when(validator.hasDueDateEqualOrGreaterThanThreeWeeks(notNull())).thenReturn(true);
        PurchaseAmountResponse result = purchaseOrderService.getAmountOfAnPurchaseOrder(purchaseOrderService.createPurchaseOrder(purchaseOrderRequest));
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldUpdateAPurchaseOrder() throws Exception {

        PurchaseAmountResponse expectedResult = new PurchaseAmountResponse(400.0);
        when(purchaseProductsRepository.findAllByPurchaseOrder(notNull())).thenReturn(new ArrayList<>(samplePurchaseOrderProductsSetToUpdate));
        when(validator.hasDueDateEqualOrGreaterThanThreeWeeks(notNull())).thenReturn(true);
        PurchaseAmountResponse result = this.purchaseOrderService.updatePurchaseOrder(purchaseOrderRequestToUpdate, 1L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void shouldListAllPurchaseOrderProducts() throws ApiException {
        Category category = new Category(1L, "FR", "Frozen");
        sampleProduct.setCategory(category);
        PurchaseOrderProducts purchaseOrderProducts = samplePurchaseOrderProductsSet.stream().findFirst().get();
        PurchaseOrderProductsResponse expectedFirstProduct = new PurchaseOrderProductsResponse(sampleProduct,purchaseOrderProducts);

        when(purchaseOrderRepository.findById(1L)).thenReturn(Optional.of(purchaseOrder));
        when(purchaseProductsRepository.findAllByPurchaseOrder(purchaseOrder)).thenReturn(List.of(purchaseOrderProducts));

        List<PurchaseOrderProductsResponse> result = purchaseOrderService.getPurchaseOrderProducts(2L);

        assertThat(result).contains(expectedFirstProduct);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenThereIsNoPurchaseOrder() {
        when(purchaseOrderRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundApiException.class,()-> purchaseOrderService.getPurchaseOrderProducts(1L));
    }
}

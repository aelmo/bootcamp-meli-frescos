package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountResponse;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseAmountResponse updatePurchaseOrder(PurchaseOrderRequest purchaseOrderRequest, Long orderId) throws ApiException;

    PurchaseOrder createPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest) throws ApiException;

    PurchaseAmountResponse getAmountOfAnPurchaseOrder(PurchaseOrder purchaseOrder);

    List<PurchaseOrderProductsResponse> getPurchaseOrderProducts(Long orderId) throws ApiException;
}

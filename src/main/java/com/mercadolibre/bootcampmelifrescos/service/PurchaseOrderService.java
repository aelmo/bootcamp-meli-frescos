package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseAmountDTO updatePurchaseOrder(PurchaseOrderDTO purchaseOrderDTO, Long orderId) throws ApiException;

    PurchaseOrder createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws ApiException;

    PurchaseAmountDTO getAmountOfAnPurchaseOrder(PurchaseOrder purchaseOrder);

    List<PurchaseOrderProductsResponse> getPurchaseOrderProducts(Long orderId) throws ApiException;
}

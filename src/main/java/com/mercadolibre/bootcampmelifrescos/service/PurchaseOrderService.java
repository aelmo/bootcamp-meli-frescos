package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;

public interface PurchaseOrderService {
    PurchaseAmountDTO createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) throws Exception;
}

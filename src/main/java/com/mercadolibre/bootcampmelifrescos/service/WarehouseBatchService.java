package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.response.WarehouseBatchResponse;

public interface WarehouseBatchService {

    WarehouseBatchResponse getWarehouseBatchQuantityByProduct(Long productId) throws Exception;
}

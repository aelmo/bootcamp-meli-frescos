package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.response.BatchStockResponse;
import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductBatchResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;

public interface BatchService {

    ProductBatchResponse getAllBatches(Long productId, String orderParam) throws ApiException;

    BatchStockResponse getByDaysBeforeDueDateAndCategory(int days, String category, String orderByDueDate) throws ApiException;
}

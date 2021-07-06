package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;

public interface InboundOrderService {
    InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException;
    InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException;
}

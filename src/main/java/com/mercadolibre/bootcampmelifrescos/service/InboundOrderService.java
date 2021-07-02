package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;

public interface InboundOrderService {
    InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception;
    InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception;
}

package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;

public interface InboundOrderConverter {

    InboundOrder dtoToEntity(InboundOrderDTO inboundOrderDTO) throws ApiException;
}

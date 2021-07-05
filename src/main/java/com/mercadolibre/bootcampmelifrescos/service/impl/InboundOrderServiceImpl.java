package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService{

    private final InboundOrderRepository inboundOrderRepository;
    private final InboundOrderConverterImpl inboundConverter;
    private final Validator validator;

    @Override
    public InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception {
        InboundOrder inboundOrder = inboundConverter.dtoToEntity(inboundOrderDTO);
        validator.validateCategorySection(inboundOrder.getSection(), inboundOrder.getBatch());
        inboundOrderRepository.save(inboundOrder);

        return new InboundOrderResponse(inboundOrderDTO.getBatchStock());
    }

    @Override
    public InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception {
        if(!inboundOrderRepository.findById(inboundOrderDTO.getOrderNumber()).isPresent()){
            throw new Exception("Inbound order with order number: " + inboundOrderDTO.getOrderNumber() + " not found to update");
        }

        return createInboundOrder(inboundOrderDTO);
    }
}

package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService{

    private final InboundOrderRepository inboundOrderRepository;
    private final InboundOrderConverterImpl inboundConverter;
    private final Validator validator;
    private final BatchRepository batchRepository;

    @Override
    public InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException {
        InboundOrder inboundOrder = inboundConverter.dtoToEntity(inboundOrderDTO);
        validator.validateCategorySection(inboundOrder.getSection(), inboundOrder.getBatch());

        Set<Batch> batchSet = inboundOrder.getBatch();
        batchSet.forEach(
                batch -> batch.setInboundOrder(inboundOrder)
        );

        inboundOrderRepository.save(inboundOrder);

        return new InboundOrderResponse(inboundOrderDTO.getBatchStock());
    }

    @Override
    public InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException {
        if(!inboundOrderRepository.findById(inboundOrderDTO.getOrderNumber()).isPresent()){
            throw new NotFoundApiException("Inbound order with order number: " + inboundOrderDTO.getOrderNumber() + " not found to update");
        }

        return createInboundOrder(inboundOrderDTO);
    }
}

package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.BadRequestApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import com.mercadolibre.bootcampmelifrescos.service.SectionService;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService{

    private final InboundOrderRepository inboundOrderRepository;
    private final InboundOrderConverterImpl inboundConverter;
    private final Validator validator;
    private final SectionService sectionService;

    @Override
    public InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException {
        InboundOrder inboundOrder = inboundConverter.dtoToEntity(inboundOrderDTO);
        Section section = inboundOrder.getSection();
        validator.validateCategorySection(inboundOrder.getSection(), inboundOrder.getBatch());

        if(!validator.hasAvailableSpaceOnSection(inboundOrderDTO))
            throw new BadRequestApiException("Section doesn't have available space");

        setOrderOnBatchStock(inboundOrder);

        int quantityOfBatches = inboundOrderDTO.getBatchStock().size();
        sectionService.updateAvailableSpace(section,quantityOfBatches);

        inboundOrderRepository.save(inboundOrder);

        return new InboundOrderResponse(inboundOrderDTO.getBatchStock());
    }

    @Override
    public InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws ApiException {
        if (inboundOrderRepository.findById(inboundOrderDTO.getOrderNumber()).isEmpty()) {
            throw new NotFoundApiException("Inbound order with order number: " + inboundOrderDTO.getOrderNumber() + " not found to update");
        }

        InboundOrder oldInboundOrder = inboundOrderRepository.findById(inboundOrderDTO.getOrderNumber()).get();
        InboundOrder newInboundOrder = inboundConverter.dtoToEntity(inboundOrderDTO);

        oldInboundOrder.setBatch(newInboundOrder.getBatch());
        oldInboundOrder.setDate(newInboundOrder.getDate());
        oldInboundOrder.setSection(newInboundOrder.getSection());
        oldInboundOrder.setUser(oldInboundOrder.getUser());

        setOrderOnBatchStock(oldInboundOrder);

        inboundOrderRepository.save(oldInboundOrder);

        return new InboundOrderResponse(inboundOrderDTO.getBatchStock());

    }


    private void setOrderOnBatchStock(InboundOrder inboundOrder) {
        Set<Batch> batchSet = inboundOrder.getBatch();
        batchSet.forEach(
                batch -> batch.setInboundOrder(inboundOrder)
        );
    }
}

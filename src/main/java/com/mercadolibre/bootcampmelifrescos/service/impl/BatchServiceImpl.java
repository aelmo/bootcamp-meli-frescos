package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.BatchResponse;
import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductBatchResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;

import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import com.mercadolibre.bootcampmelifrescos.service.BatchService;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;
    private final Validator validator;

    public ProductBatchResponse getAllBatches(Long productId, String orderParam) throws ApiException {
        List<Batch> batches = batchRepository.findAllByProductId(productId);

        if(batches.isEmpty()) {
            throw new NotFoundApiException("No batches found for product with id: " + productId);
        }

        List<BatchResponse> batchResponseList = getOrderedBatchResponseList(batches, orderParam);
        SectionDTO sectionDTO = getSectionDTO(batches);

        return new ProductBatchResponse(sectionDTO, productId, batchResponseList);
    }

    private List<BatchResponse> getOrderedBatchResponseList(List<Batch> batches, String orderParam) {
        List<BatchResponse> batchResponseList =  new ArrayList<>();
        for(Batch batch : batches) {
            if(validator.hasDueDateEqualOrGreaterThanThreeWeeks(batch)) {
                batchResponseList.add(new BatchResponse(batch));
            }
        }
        setBatchResponseListOrder(batchResponseList, orderParam);

        return batchResponseList;
    }

    private void setBatchResponseListOrder(List<BatchResponse> batchResponseList, String orderParam) {
        if(orderParam.equalsIgnoreCase("C")) {
            batchResponseList.sort(Comparator.comparing(BatchResponse::getCurrentQuantity));
        }

        if(orderParam.equalsIgnoreCase("F")) {
            batchResponseList.sort(Comparator.comparing(BatchResponse::getDueDate));
        }
    }

    private SectionDTO getSectionDTO(List<Batch> batches) {
        Section section = batches.stream().findFirst().get().getInboundOrder().getSection();
        SectionDTO sectionDTO = new SectionDTO(section.getCategory().getId(), section.getWarehouse().getId());
        return sectionDTO;
    }
}

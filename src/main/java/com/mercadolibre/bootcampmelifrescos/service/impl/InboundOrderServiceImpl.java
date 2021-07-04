package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.SectionRepository;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InboundOrderServiceImpl implements InboundOrderService{

    private final InboundOrderRepository inboundOrderRepository;
    private final SectionRepository sectionRepository;
    private final ProductRepository productRepository;

    @Override
    public InboundOrderResponse createInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception {
        Section section = sectionRepository.findById(inboundOrderDTO.getSectionCode()).orElseThrow();
        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        Set<Batch> batchSet = new HashSet<>();

        for (BatchDTO batchDTO : batchDTOList){
            Product product = productRepository.findById(batchDTO.getProductId()).orElseThrow();
            batchSet.add(new Batch(batchDTO, product));
        }

        validateCategorySection(section, batchSet);

        InboundOrder inboundOrder = new InboundOrder(inboundOrderDTO, batchSet, section);
        inboundOrderRepository.save(inboundOrder);

        return new InboundOrderResponse(batchDTOList);
    }

    @Override
    public InboundOrderResponse updateInboundOrder(InboundOrderDTO inboundOrderDTO) throws Exception {
        if(!inboundOrderRepository.findById(inboundOrderDTO.getOrderNumber()).isPresent()){
            return null;
        }
        return createInboundOrder(inboundOrderDTO);
    }

    public void validateCategorySection(Section section, Set<Batch> batchList) throws Exception {
        for (Batch batch : batchList){
            Product product = batch.getProduct();
            if(product.getCategory().getId()!=section.getCategory().getId())
                throw new Exception("Invalid category");
        }
    }

}

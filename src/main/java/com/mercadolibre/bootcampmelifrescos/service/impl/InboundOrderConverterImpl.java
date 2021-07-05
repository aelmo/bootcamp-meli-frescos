package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.InboundOrder;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.SectionRepository;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InboundOrderConverterImpl implements InboundOrderConverter {
    private SectionRepository sectionRepository;
    private ProductRepository productRepository;

    public InboundOrder dtoToEntity(InboundOrderDTO inboundOrderDTO) {
        Section section = sectionRepository.findById(inboundOrderDTO.getSectionCode()).orElseThrow();
        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        Set<Batch> batchSet = new HashSet<>();

        for (BatchDTO batchDTO : batchDTOList){
            Product product = productRepository.findById(batchDTO.getProductId()).orElseThrow();
            batchSet.add(new Batch(batchDTO, product));
        }

        return new InboundOrder(inboundOrderDTO, batchSet, section);
    }
}

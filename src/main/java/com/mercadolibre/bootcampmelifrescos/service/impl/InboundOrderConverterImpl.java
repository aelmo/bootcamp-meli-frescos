package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
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

    public InboundOrder dtoToEntity(InboundOrderDTO inboundOrderDTO) throws NotFoundApiException {
        Long sectionId = inboundOrderDTO.getSectionCode();

        Section section = sectionRepository.findById(sectionId).orElseThrow(
                () -> new NotFoundApiException("Section with id: " + sectionId + " not found")
        );

        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        Set<Batch> batchSet = new HashSet<>();

        for (BatchDTO batchDTO : batchDTOList){
            Long productId = batchDTO.getProductId();
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new NotFoundApiException("Product with id: " + productId + " not found")
            );
            batchSet.add(new Batch(batchDTO, product));
        }

        return new InboundOrder(inboundOrderDTO, batchSet, section);
    }
}

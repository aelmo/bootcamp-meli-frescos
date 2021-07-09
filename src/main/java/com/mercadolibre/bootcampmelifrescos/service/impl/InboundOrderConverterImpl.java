package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.*;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.repository.SectionRepository;
import com.mercadolibre.bootcampmelifrescos.repository.WarehouseRepository;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import com.mercadolibre.bootcampmelifrescos.repository.UserRepository;
import com.mercadolibre.bootcampmelifrescos.security.AuthenticationFacade;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderConverter;
import com.mercadolibre.bootcampmelifrescos.util.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InboundOrderConverterImpl implements InboundOrderConverter {

    private UserRepository userRepository;
    private SectionRepository sectionRepository;
    private ProductRepository productRepository;
    private AuthenticationFacade authenticationFacade;
    private WarehouseRepository warehouseRepository;
    private final Validator validator;

    public InboundOrder dtoToEntity(InboundOrderDTO inboundOrderDTO) throws ApiException {
        Long sectionId = inboundOrderDTO.getSectionCode();
        Long warehouseId = inboundOrderDTO.getSection().getWarehouseCode();

        MyUserDetails userDetails = authenticationFacade.getUserDetails();
        User user = userRepository.findByUserName(userDetails.getUsername());

        Section section = sectionRepository.findById(sectionId).orElseThrow(
                () -> new NotFoundApiException("Section with id " + sectionId + " not found")
        );

        validator.validateWarehouseSection(sectionId, warehouseId, section);

        List<BatchDTO> batchDTOList = inboundOrderDTO.getBatchStock();
        Set<Batch> batchSet = new HashSet<>();

        for (BatchDTO batchDTO : batchDTOList){
            Long productId = batchDTO.getProductId();
            Product product = productRepository.findById(productId).orElseThrow(
                    () -> new NotFoundApiException("Product with id: " + productId + " not found")
            );
            batchSet.add(new Batch(batchDTO, product));
        }

        return new InboundOrder(inboundOrderDTO, batchSet, section, user);
    }
}

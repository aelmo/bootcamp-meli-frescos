package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.BadRequestApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.model.Warehouse;
import com.mercadolibre.bootcampmelifrescos.repository.WarehouseRepository;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Service
@AllArgsConstructor
public class ValidatorImpl implements Validator {
    private WarehouseRepository warehouseRepository;

    @Override
    public void validateCategorySection(Section section, Set<Batch> batchSet) throws ApiException {
        for (Batch batch : batchSet){
            Product product = batch.getProduct();
            if(product.getCategory().getCode() != section.getCategory().getCode())
                throw new BadRequestApiException("Product with id: " + product.getId() + " is in an incompatible section");
        }
    }

    @Override
    public boolean hasDueDateEqualOrGreaterThanThreeWeeks(Batch batch) {
        return ChronoUnit.DAYS.between(LocalDate.now(), batch.getDueDate()) >= 21;
    }

    @Override
    public void validateWarehouseSection(Long sectionId, Long warehouseId, Section section) throws ApiException {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(
                () -> new NotFoundApiException("Warehouse with id " + warehouseId + " not found")
        );

        if(!warehouse.getSections().contains(section))
            throw new BadRequestApiException("Section " + sectionId + " don't belong to warehouse " + warehouseId);
    }

}

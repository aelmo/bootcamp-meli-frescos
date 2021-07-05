package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.exceptions.ApiException;
import com.mercadolibre.bootcampmelifrescos.exceptions.BadRequestApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.service.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidatorImpl implements Validator {

    @Override
    public void validateCategorySection(Section section, Set<Batch> batchSet) throws ApiException {
        for (Batch batch : batchSet){
            Product product = batch.getProduct();
            if(product.getCategory().getCode() != section.getCategory().getCode())
                throw new BadRequestApiException("Product with id: " + product.getId() + " is in an incompatible section");
        }
    }
}

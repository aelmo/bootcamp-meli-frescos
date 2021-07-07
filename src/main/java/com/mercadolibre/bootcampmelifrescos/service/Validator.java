package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Section;

import java.util.Set;

public interface Validator {
    void validateCategorySection(Section section, Set<Batch> batchSet) throws ApiException;
}

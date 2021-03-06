package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.model.Section;

import java.util.Set;

public interface Validator {

    void validateCategorySection(Section section, Set<Batch> batchSet) throws ApiException;

    boolean hasDueDateEqualOrGreaterThanThreeWeeks(Batch batch);

    void validateWarehouseSection(Long sectionId, Long warehouseId, Section section) throws ApiException;

    boolean hasAvailableSpaceOnSection(InboundOrderDTO inboundOrderDTO) throws ApiException;
}

package com.mercadolibre.bootcampmelifrescos.service;

import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Section;

public interface SectionService {
    void updateAvailableSpace(Section section, int quantityOfBatches) throws NotFoundApiException;
}

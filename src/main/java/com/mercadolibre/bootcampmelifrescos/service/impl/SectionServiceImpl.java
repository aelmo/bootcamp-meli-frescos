package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.exceptions.api.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Section;
import com.mercadolibre.bootcampmelifrescos.repository.SectionRepository;
import com.mercadolibre.bootcampmelifrescos.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private SectionRepository sectionRepository;

    @Override
    public void updateAvailableSpace(Section section, int quantityOfBatches) throws NotFoundApiException {
        int newAvailableSpace = section.getAvailableSpace() - quantityOfBatches;
        Section updateSection = sectionRepository.findById(section.getId()).orElseThrow(
                () -> new NotFoundApiException("Section not found")
        );

        updateSection.setAvailableSpace(newAvailableSpace);
        sectionRepository.save(updateSection);
    }
}

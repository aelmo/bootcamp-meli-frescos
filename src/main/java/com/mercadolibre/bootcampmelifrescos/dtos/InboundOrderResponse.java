package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderResponse {
    private List<BatchDTO> batchStock;
}

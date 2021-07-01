package com.mercadolibre.bootcampmelifrescos.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
    private int batchNumber;
    private Long productId;
    private Float currentTemperature;
    private Float minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
}

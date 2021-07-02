package com.mercadolibre.bootcampmelifrescos.dtos;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {
    private Long batchNumber;
    private Long productId;
    private Float currentTemperature;
    private Float minimumTemperature;
    private int initialQuantity;
    private int currentQuantity;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private LocalDate dueDate;
}

package com.mercadolibre.bootcampmelifrescos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    @JsonInclude(Include.NON_NULL)
    @Positive
    @NotBlank
    private Long batchNumber;

    @Positive
    @NotBlank
    private Long productId;

    @NotBlank
    private Float currentTemperature;

    @NotBlank
    private Float minimumTemperature;

    @Positive
    @NotBlank
    private int initialQuantity;

    @Positive
    @NotBlank
    private int currentQuantity;

    @NotBlank
    private LocalDate manufacturingDate;

    @NotBlank
    private LocalDateTime manufacturingTime;

    @NotBlank
    private LocalDate dueDate;
}

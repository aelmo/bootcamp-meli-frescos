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
    @NotBlank(message = "The batch number can't be empty")
    private Long batchNumber;

    @Positive
    @NotBlank(message = "The product id can't be empty")
    private Long productId;

    @NotBlank(message = "The current temperature can't be empty")
    private Float currentTemperature;

    @NotBlank(message = "The minimum temperature can't be empty")
    private Float minimumTemperature;

    @Positive
    @NotBlank(message = "The initial quantity can't be empty")
    private int initialQuantity;

    @Positive
    @NotBlank(message = "The current quantity can't be empty")
    private int currentQuantity;

    @NotBlank(message = "The manufacturing date can't be empty")
    private LocalDate manufacturingDate;

    @NotBlank(message = "The manufacturing time can't be empty")
    private LocalDateTime manufacturingTime;

    @NotBlank(message = "The due date can't be empty")
    private LocalDate dueDate;
}

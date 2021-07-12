package com.mercadolibre.bootcampmelifrescos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchDTO {

    @JsonInclude(Include.NON_NULL)
    private Long batchNumber;

    @Positive(message = "The product id must be a positive number")
    @NotNull(message = "The product id can't be empty")
    private Long productId;

    @NotNull(message = "The current temperature can't be empty")
    private Float currentTemperature;

    @NotNull(message = "The minimum temperature can't be empty")
    private Float minimumTemperature;

    @Positive(message = "The initial quantity must be a positive number")
    @NotNull(message = "The initial quantity can't be empty")
    private int initialQuantity;

    @Positive(message = "The current quantity must be a positive number")
    @NotNull(message = "The current quantity can't be empty")
    private int currentQuantity;

    @NotNull(message = "The manufacturing date can't be empty")
    private LocalDate manufacturingDate;

    @NotNull(message = "The manufacturing time can't be empty")
    private LocalDateTime manufacturingTime;

    @NotNull(message = "The due date can't be empty")
    private LocalDate dueDate;
}

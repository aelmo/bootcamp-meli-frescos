package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDashBoardDTO {


    @NotNull(message="The start date cannot be null")
    private LocalDate startDate;

    @NotNull(message="The end date cannot be null")
    private LocalDate endDate;
}

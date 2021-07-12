package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDashBoardDTO {

    @NotBlank(message="The start date cannot be null")
    private LocalDate startDate;

    private LocalDate endDate;
}

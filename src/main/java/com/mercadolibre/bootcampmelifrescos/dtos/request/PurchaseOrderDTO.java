package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    @NotNull(message = "The purchase date cannot be null")
    private LocalDate date;
    @NotNull(message="The buyer id cannot be null")
    private Long buyerId;

    @Valid
    private PurchaseOrderStatusDTO orderStatus;

    @Valid
    private Set<PurchaseRequestProductsDTO> products;
}

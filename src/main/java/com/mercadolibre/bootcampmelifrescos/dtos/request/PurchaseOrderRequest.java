package com.mercadolibre.bootcampmelifrescos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderRequest {

    @NotNull(message = "The purchase date cannot be null")
    private LocalDate date;

    @NotNull(message = "The buyer id cannot be null")
    @Positive(message = "The buyer id must be a positive number")
    private Long buyerId;

    @Valid
    private PurchaseOrderStatusRequest orderStatus;

    @Valid
    private Set<PurchaseRequestProductsRequest> products;
}

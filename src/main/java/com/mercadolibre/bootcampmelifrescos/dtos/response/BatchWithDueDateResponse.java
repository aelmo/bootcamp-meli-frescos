package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchWithDueDateResponse {
    private Long batchNumber;
    private Long productId;
    private String productTypeId;
    private LocalDate dueDate;
    private int quantity;

    public BatchWithDueDateResponse(Batch batch) {
        this.batchNumber = batch.getId();
        this.productId = batch.getProduct().getId();
        this.productTypeId = batch.getProduct().getCategory().getCode();
        this.dueDate = batch.getDueDate();
        this.quantity = batch.getCurrentQuantity();
    }
}

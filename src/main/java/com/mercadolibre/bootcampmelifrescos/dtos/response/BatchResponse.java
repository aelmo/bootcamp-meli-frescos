package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchResponse {
    private Long batchNumber;
    private int currentQuantity;
    private LocalDate dueDate;

    public BatchResponse(Batch batch) {
        this.batchNumber = batch.getId();
        this.currentQuantity = batch.getCurrentQuantity();
        this.dueDate = batch.getDueDate();
    }
}

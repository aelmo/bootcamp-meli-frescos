package com.mercadolibre.bootcampmelifrescos.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockResponse {

    private List<BatchWithDueDateResponse> batchStock;
}

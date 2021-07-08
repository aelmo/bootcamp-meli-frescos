package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.dtos.WarehouseBatchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseBatchResponse {
    private Long productId;
    private List<WarehouseBatchDTO> warehouses;
}

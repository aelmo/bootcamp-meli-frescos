package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderResponse {

    private List<BatchDTO> batchStock;
}

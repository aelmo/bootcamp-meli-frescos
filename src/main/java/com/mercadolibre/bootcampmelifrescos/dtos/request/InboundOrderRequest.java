package com.mercadolibre.bootcampmelifrescos.dtos.request;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequest {

    @Valid
    private InboundOrderDTO inboundOrder;

    public List<BatchDTO> getBatchDTOList(){
        return inboundOrder.getBatchStock();
    }
}

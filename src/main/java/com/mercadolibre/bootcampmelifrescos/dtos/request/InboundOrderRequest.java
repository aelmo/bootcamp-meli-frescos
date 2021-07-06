package com.mercadolibre.bootcampmelifrescos.dtos.request;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderRequest {
    private InboundOrderDTO inboundOrder;

    public List<BatchDTO> getBatchDTOList(){
        return inboundOrder.getBatchStock();
    }

    public LocalDate getOrderDate(){
        return inboundOrder.getOrderDate();
    }

    public SectionDTO getSectionOrder(){
        return inboundOrder.getSection();
    }
}

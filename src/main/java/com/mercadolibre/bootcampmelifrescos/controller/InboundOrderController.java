package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.InboundOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path="/api/v1/fresh-products/inboundorder/")
@RestController
@AllArgsConstructor
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    @PostMapping
    public ResponseEntity<InboundOrderResponse> createInboundOrder(@RequestBody InboundOrderRequest inboundOrderRequest){
        return new ResponseEntity<>(inboundOrderService.createInboundOrder(inboundOrderRequest), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<InboundOrderResponse> updateInboundOrder(@RequestBody InboundOrderRequest inboundOrderRequest){
        return new ResponseEntity<>(inboundOrderService.updateInboundOrder(inboundOrderRequest), HttpStatus.CREATED);
    }

}

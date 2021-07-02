package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.InboundOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path="/api/v1/fresh-products/inboundorder/")
@RestController
@AllArgsConstructor
public class InboundOrderController {

    private final InboundOrderService inboundOrderService;

    @PostMapping
    public ResponseEntity<InboundOrderResponse> createInboundOrder(@Valid @RequestBody InboundOrderRequest inboundOrderRequest) throws Exception {
        return new ResponseEntity<>(inboundOrderService.createInboundOrder(inboundOrderRequest.getInboundOrder()), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<InboundOrderResponse> updateInboundOrder(@Valid @RequestBody InboundOrderRequest inboundOrderRequest) throws Exception {
        return new ResponseEntity<>(inboundOrderService.updateInboundOrder(inboundOrderRequest.getInboundOrder()), HttpStatus.CREATED);
    }

}

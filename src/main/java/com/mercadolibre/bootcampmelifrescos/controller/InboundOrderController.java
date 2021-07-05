package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.InboundOrderRequest;
import com.mercadolibre.bootcampmelifrescos.dtos.response.InboundOrderResponse;
import com.mercadolibre.bootcampmelifrescos.service.InboundOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create Inbound Order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inbound order created with success"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping
    public ResponseEntity<InboundOrderResponse> createInboundOrder(@ApiParam(value = "Object for creating a new inbound order", required = true)
                                                                       @Valid @RequestBody InboundOrderRequest inboundOrderRequest) throws Exception {
        return new ResponseEntity<>(inboundOrderService.createInboundOrder(inboundOrderRequest.getInboundOrder()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update Inbound Order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Inbound order updated with success"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PutMapping
    public ResponseEntity<InboundOrderResponse> updateInboundOrder(@ApiParam(value = "Object for update an inbound order", required = true)
                                                                       @Valid @RequestBody InboundOrderRequest inboundOrderRequest) throws Exception {
        return new ResponseEntity<>(inboundOrderService.updateInboundOrder(inboundOrderRequest.getInboundOrder()), HttpStatus.CREATED);
    }
}

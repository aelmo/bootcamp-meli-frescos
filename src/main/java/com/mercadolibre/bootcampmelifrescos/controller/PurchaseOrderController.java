package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/api/v1/fresh-products")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping("/orders/")
    public ResponseEntity insertNewPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        try {
            return new ResponseEntity(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

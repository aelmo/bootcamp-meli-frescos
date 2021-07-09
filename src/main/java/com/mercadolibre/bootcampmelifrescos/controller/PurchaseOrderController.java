package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.request.PurchaseOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseAmountDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.PurchaseOrderProductsResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/fresh-products")
@AllArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @ApiOperation(value = "Create a purchase order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Purchase order created with success"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PostMapping("/orders/")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity insertNewPurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        try {
            return new ResponseEntity(purchaseOrderService.getAmountOfAnPurchaseOrder(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO)), HttpStatus.CREATED);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update a purchase order")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Purchase order updated with success"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @PutMapping("/orders/")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<PurchaseAmountDTO> updatePurchaseOrder(@Valid @RequestBody PurchaseOrderDTO purchaseOrderDTO,
                                                             @RequestParam(name = "idOrder",
                                                                         defaultValue = "")
                                                                         Long idOrder) throws Exception {
        return new ResponseEntity<>(purchaseOrderService.updatePurchaseOrder(purchaseOrderDTO, idOrder), HttpStatus.OK);
    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<PurchaseOrderProductsResponse>> getPurchaseOrderProducts(@RequestParam(name = "idOrder") Long idOrder) throws ApiException {
        return new ResponseEntity<>(purchaseOrderService.getPurchaseOrderProducts(idOrder),HttpStatus.OK);
    }

}

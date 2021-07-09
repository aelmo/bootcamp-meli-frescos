package com.mercadolibre.bootcampmelifrescos.controller;


import com.mercadolibre.bootcampmelifrescos.dtos.response.WarehouseBatchResponse;
import com.mercadolibre.bootcampmelifrescos.service.WarehouseBatchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fresh-products/warehouse")
@AllArgsConstructor
public class WarehouseBatchController {
    private final WarehouseBatchService warehouseBatchService;

    @GetMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity<WarehouseBatchResponse> getWarehouseBatchQuantityByProduct(@PathVariable("productId") Long productId) throws Exception {
        return new ResponseEntity<>(warehouseBatchService.getWarehouseBatchQuantityByProduct(productId), HttpStatus.OK);
    }
}

package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.response.ProductAmountResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.service.ProductAmountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
@AllArgsConstructor
public class ProductAmountController {

    private final ProductAmountService productService;

    @GetMapping("/amount")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<ProductAmountResponse>> getAllProductsWithAmount(@RequestParam(required = false, defaultValue = "null") String order
    ) throws ApiException {
        return new ResponseEntity<>(productService.getAllProductsWithAmount(order), HttpStatus.OK);
    }

   @GetMapping("/list/amount")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<ProductAmountResponse>> getProductsByCategoryWithAmount(@RequestParam(name = "category") String categoryCode,
                                                                  @RequestParam(required = false, defaultValue = "null") String order
    ) throws ApiException {
        return new ResponseEntity<>(productService.getProductsByCategoryWithAmount(categoryCode, order), HttpStatus.OK);
    }
}

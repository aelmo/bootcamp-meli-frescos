package com.mercadolibre.bootcampmelifrescos.controller;

import com.mercadolibre.bootcampmelifrescos.dtos.ProductDTO;
import com.mercadolibre.bootcampmelifrescos.exceptions.api.ApiException;
import com.mercadolibre.bootcampmelifrescos.service.BatchService;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final BatchService batchService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws ApiException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_BUYER')")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam(name = "category") String categoryCode) throws ApiException {
        return new ResponseEntity<>(productService.getProductsByCategory(categoryCode), HttpStatus.OK);
    }

    @GetMapping("/list/batches")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity getProductsBatch(@RequestParam Long productId, @RequestParam(required = false) String orderParam) throws ApiException {
        return new ResponseEntity<>(batchService.getAllBatches(productId, orderParam), HttpStatus.OK);
    }

    @GetMapping("/due-date/list")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity getBatchByDaysBeforeDueDate(
            @RequestParam int days,
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "asc") String orderByDueDate
    ) throws ApiException {
        return new ResponseEntity<>(batchService.getByDaysBeforeDueDateAndCategory(days, category, orderByDueDate), HttpStatus.OK);
    }

    @GetMapping("/best-sellers")
    @PreAuthorize("hasRole('ROLE_AGENT')")
    public ResponseEntity getBestSellers(
            @RequestParam(required = false, defaultValue = "3") int numberOfProducts
    ) throws ApiException {
        return new ResponseEntity<>(productService.getBestSellers(numberOfProducts), HttpStatus.OK);
    }
}

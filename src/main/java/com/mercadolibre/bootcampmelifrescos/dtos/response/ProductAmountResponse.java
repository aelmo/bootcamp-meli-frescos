package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductAmountResponse {

    private String name;
    private String category;
    private Float amount;

    public ProductAmountResponse(Product product){
        this.name = product.getName();
        this.category = product.getCategory().getDescription();
        this.amount = product.getAmount();
    }
}

package com.mercadolibre.bootcampmelifrescos.dtos;

import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private String category;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.category = product.getCategory().getDescription();
    }
}

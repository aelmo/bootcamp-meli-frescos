package com.mercadolibre.bootcampmelifrescos.dtos;

import com.mercadolibre.bootcampmelifrescos.model.Category;
import com.mercadolibre.bootcampmelifrescos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.category = product.getCategory().getDescription();
    }
}

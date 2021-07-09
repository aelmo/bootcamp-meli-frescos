package com.mercadolibre.bootcampmelifrescos.dtos;

import com.mercadolibre.bootcampmelifrescos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "The name can't be empty")
    private String name;

    @NotBlank(message = "The category can't be empty")
    private String category;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.category = product.getCategory().getDescription();
    }
}

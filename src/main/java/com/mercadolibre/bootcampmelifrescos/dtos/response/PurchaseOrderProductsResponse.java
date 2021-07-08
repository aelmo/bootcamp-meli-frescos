package com.mercadolibre.bootcampmelifrescos.dtos.response;

import com.mercadolibre.bootcampmelifrescos.model.Product;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrderProducts;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseOrderProductsResponse {
    private String name;
    private String category;
    private int quantity;

    public PurchaseOrderProductsResponse(Product product, PurchaseOrderProducts purchaseOrderProducts){
        this.name = product.getName();
        this.category = product.getCategory().getDescription();
        this.quantity = purchaseOrderProducts.getQuantity();
    }
}

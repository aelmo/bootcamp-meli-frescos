package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="purchase_products")
@Data
@AllArgsConstructor
@NoArgsConstructor

@EqualsAndHashCode(exclude = {"product","purchaseOrder"})
public class PurchaseOrderProducts implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Product product;

    @AttributeOverride(name = "id", column = @Column(name = "purchase_id", nullable = false))
    @ManyToOne(cascade = CascadeType.ALL)
    private PurchaseOrder purchaseOrder;

    @Column(name = "quantity")
    private Integer quantity;
}

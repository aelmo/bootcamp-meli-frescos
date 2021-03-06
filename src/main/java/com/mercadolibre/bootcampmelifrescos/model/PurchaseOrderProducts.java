package com.mercadolibre.bootcampmelifrescos.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="purchase_products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"product", "purchaseOrder"})
@EqualsAndHashCode(exclude = {"product","purchaseOrder"})
public class PurchaseOrderProducts implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Product product;

    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "batch_id")
    private Long batchId;


}

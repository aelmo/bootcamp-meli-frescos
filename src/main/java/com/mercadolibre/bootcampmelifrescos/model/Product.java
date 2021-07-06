package com.mercadolibre.bootcampmelifrescos.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"seller", "purchaseOrderProducts"})
@ToString(exclude = {"purchaseOrderProducts", "seller"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @Column(name="amount")
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PurchaseOrderProducts> purchaseOrderProducts;
}

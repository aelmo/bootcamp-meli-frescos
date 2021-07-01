package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="sellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "seller")
    private Set<ProductModel> products;
}

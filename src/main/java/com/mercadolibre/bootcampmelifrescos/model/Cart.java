package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private Set<Product> products;
}

package com.mercadolibre.bootcampmelifrescos.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="seller")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "seller")
    private Set<Product> products;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;
}

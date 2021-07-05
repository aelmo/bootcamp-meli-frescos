package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="purchase_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PurchaseStatuses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;
}

package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="purchase_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name="status", referencedColumnName = "id")
    private PurchaseStatuses status;

    @OneToOne
    private Cart cart;
}

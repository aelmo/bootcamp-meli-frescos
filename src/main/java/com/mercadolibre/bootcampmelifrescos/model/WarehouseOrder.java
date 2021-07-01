package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="warehouse_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne()
    private Section section;

    @OneToMany(mappedBy = "warehouseOrder")
    private Set<Batch> batch;

    @OneToOne()
    private Agent agent;
}

package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="batch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_temperature")
    private Float currentTemperature;

    @Column(name = "minimum_temperature")
    private Float minimumTemperature;

    @Column(name = "maximum_temperature")
    private Float maximumTemperature;

    @Column(name = "initial_quantity")
    private Float initialQuantity;

    @Column(name = "current_quantity")
    private Float currentQuantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time")
    private LocalDateTime manufacturingTime;

    @Column(name="due_date")
    private LocalDate dueDate;

    @OneToMany(targetEntity = Product.class, mappedBy = "batch")
    private Set<Product> product;

    @ManyToOne(targetEntity = WarehouseOrder.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private WarehouseOrder warehouseOrder;
}

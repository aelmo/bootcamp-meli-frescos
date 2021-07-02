package com.mercadolibre.bootcampmelifrescos.model;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_temperature")
    private Float currentTemperature;

    @Column(name = "minimum_temperature")
    private Float minimumTemperature;

    @Column(name = "initial_quantity")
    private int initialQuantity;

    @Column(name = "current_quantity")
    private int currentQuantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time")
    private LocalDateTime manufacturingTime;

    @Column(name="due_date")
    private LocalDate dueDate;

    @OneToOne
    private Product product;

    @ManyToOne(targetEntity = InboundOrder.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private InboundOrder inboundOrder;

    public Batch(BatchDTO batchDTO, Product product){
        this.currentTemperature = batchDTO.getCurrentTemperature();
        this.minimumTemperature = batchDTO.getMinimumTemperature();
        this.initialQuantity = batchDTO.getInitialQuantity();
        this.currentQuantity = batchDTO.getCurrentQuantity();
        this.manufacturingDate = batchDTO.getManufacturingDate();
        this.manufacturingTime = batchDTO.getManufacturingTime();
        this.dueDate = batchDTO.getDueDate();
        this.product = product;
    }
}

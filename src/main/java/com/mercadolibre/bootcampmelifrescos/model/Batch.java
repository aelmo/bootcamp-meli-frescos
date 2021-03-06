package com.mercadolibre.bootcampmelifrescos.model;

import com.mercadolibre.bootcampmelifrescos.dtos.BatchDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="batch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "inboundOrder")
@ToString(exclude = "inboundOrder")
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

    @Column(name = "last_quantity")
    private int lastQuantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time")
    private LocalDateTime manufacturingTime;

    @Column(name="due_date")
    private LocalDate dueDate;

    @OneToOne
    private Product product;

    @ManyToOne(targetEntity = InboundOrder.class)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private InboundOrder inboundOrder;

    public Batch(BatchDTO batchDTO, Product product){
        this.id = batchDTO.getBatchNumber();
        this.currentTemperature = batchDTO.getCurrentTemperature();
        this.minimumTemperature = batchDTO.getMinimumTemperature();
        this.initialQuantity = batchDTO.getInitialQuantity();
        this.currentQuantity = batchDTO.getCurrentQuantity();
        this.manufacturingDate = batchDTO.getManufacturingDate();
        this.manufacturingTime = batchDTO.getManufacturingTime();
        this.dueDate = batchDTO.getDueDate();
        this.product = product;
    }

    public Batch(long id, float currentTemperature, float minimumTemperature, int initialQuantity, int currentQuantity, LocalDate manufacturingDate, LocalDateTime manufacturingTime, LocalDate dueDate, Product product, InboundOrder inboundOrder) {
        this.id = id;
        this.currentTemperature = currentTemperature;
        this.minimumTemperature = minimumTemperature;
        this.initialQuantity = initialQuantity;
        this.currentQuantity = currentQuantity;
        this.lastQuantity = currentQuantity;
        this.manufacturingDate = manufacturingDate;
        this.manufacturingTime = manufacturingTime;
        this.dueDate = dueDate;
        this.product = product;
        this.inboundOrder = inboundOrder;
    }

    public Long getWarehouseId(){
        return inboundOrder.getSection().getWarehouseId();
    }
}

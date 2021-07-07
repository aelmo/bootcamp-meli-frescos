package com.mercadolibre.bootcampmelifrescos.model;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.InboundOrderRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="inbound_order")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne()
    private Section section;

    @OneToMany(mappedBy = "inboundOrder", cascade = CascadeType.ALL)
    private Set<Batch> batch;

    @OneToOne()
    private Agent agent;

    public InboundOrder(InboundOrderDTO inboundOrderDTO, Set<Batch> batchSet, Section section){

        this.date = inboundOrderDTO.getOrderDate();
        this.section = section;
        this.batch = batchSet;
    }

    public Long getWarehouseId(){
        return section.getWarehouseId();
    }
}

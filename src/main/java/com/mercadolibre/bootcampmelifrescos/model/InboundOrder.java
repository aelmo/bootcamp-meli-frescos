package com.mercadolibre.bootcampmelifrescos.model;

import com.mercadolibre.bootcampmelifrescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.request.InboundOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="inbound_order")
@Data
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

    @OneToMany(mappedBy = "inboundOrder")
    private Set<Batch> batch;

    @OneToOne()
    private Agent agent;

    public InboundOrder(InboundOrderDTO inboundOrderDTO, Set<Batch> batchSet, Section section){
        this.date = inboundOrderDTO.getOrderDate();
        this.section = section;
        this.batch = batchSet;
    }
}

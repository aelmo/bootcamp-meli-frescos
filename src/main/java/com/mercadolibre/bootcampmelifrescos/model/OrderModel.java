package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @OneToOne()
    private SectionModel section;

    @OneToMany(mappedBy = "order")
    private Set<BatchModel> batch;

    @OneToOne()
    private AgentModel agent;
}

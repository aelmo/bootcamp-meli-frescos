package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agents")
public class AgentModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}

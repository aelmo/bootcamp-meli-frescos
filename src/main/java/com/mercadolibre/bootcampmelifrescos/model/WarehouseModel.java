package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
public class WarehouseModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="warehouse_name")
    private String warehouseName;

    @OneToMany(targetEntity = SectionModel.class, mappedBy = "warehouse")
    private Set<SectionModel> sections;
}

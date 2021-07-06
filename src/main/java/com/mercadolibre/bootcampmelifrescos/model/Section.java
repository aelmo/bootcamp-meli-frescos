package com.mercadolibre.bootcampmelifrescos.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="section")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "warehouse")
@ToString(exclude = "warehouse")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;

    @OneToOne
    private Category category;

}
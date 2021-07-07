package com.mercadolibre.bootcampmelifrescos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(
                name = "user_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
        ))
    private Set<Role> roles;
}

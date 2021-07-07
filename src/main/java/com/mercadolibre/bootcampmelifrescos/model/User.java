package com.mercadolibre.bootcampmelifrescos.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean enabled;

    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(
                name = "user_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
        ))
    private List<Role> roles;
}

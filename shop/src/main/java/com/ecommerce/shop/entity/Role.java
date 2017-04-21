package com.ecommerce.shop.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dguzik
 */
@Entity
@Table(name = "roles")
public class Role extends EntityId {

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String name;

    @Column(length=1024)
    private String description;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name="role_permission",
            joinColumns={@JoinColumn(name="role_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="premission_id", referencedColumnName="id")})
    private List<Permission> permissions;
}

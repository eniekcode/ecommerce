package com.ecommerce.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by dguzik
 */
@Entity
@Table(name = "permissions")
public class Permission extends EntityId {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1024)
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}

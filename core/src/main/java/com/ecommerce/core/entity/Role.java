package com.ecommerce.core.entity;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}

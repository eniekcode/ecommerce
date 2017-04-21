package com.ecommerce.core.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by dguzik
 */
@Entity
@Table(name = "categories")
public class Category extends EntityId {

    @Column(nullable=false, unique=true)
    @NotEmpty
    private String name;

    @Column(length=1024)
    private String description;

    private Integer displayOrder;

    private boolean disabled;

    @OneToMany(mappedBy="category")
    private Set<Product> products;
}

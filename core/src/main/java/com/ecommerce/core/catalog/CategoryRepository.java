package com.ecommerce.core.catalog;

import com.ecommerce.core.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dguzik
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getByName(String name);
}

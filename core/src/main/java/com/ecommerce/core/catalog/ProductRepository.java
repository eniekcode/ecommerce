package com.ecommerce.core.catalog;

import com.ecommerce.core.entity.Category;
import com.ecommerce.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dguzik
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findByName(String name);

    Product findBySku(String sku);
    @Query("select p from Product p where p.name like ?1 or p.sku like ?1 or p.description like ?1")
    List<Product> search(String query);
}

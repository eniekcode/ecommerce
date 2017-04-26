package com.ecommerce.core.orders;

import com.ecommerce.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dguzik
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderNumber(String orderNumber);
}

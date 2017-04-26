package com.ecommerce.core.customer;

import com.ecommerce.core.entity.Customer;
import com.ecommerce.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dguzik
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmail(String email);

    @Query("select o from Order o where o.customer.email=?1")
    List<Order> getCustomerOrders(String email);
}

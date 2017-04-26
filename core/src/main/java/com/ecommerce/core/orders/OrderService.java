package com.ecommerce.core.orders;

import com.ecommerce.core.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dguzik
 */
@Service
@Transactional
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(Order order)
    {
        //order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderNumber(String.valueOf(System.currentTimeMillis()));
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

    public Order getOrder(String orderNumber)
    {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    public List<Order> getAllOrders()
    {
        Sort sort = new Sort(Sort.Direction.DESC, "createdOn");
        return orderRepository.findAll(sort);
    }

    public Order updateOrder(Order order) {
        Order o = getOrder(order.getOrderNumber());
        o.setStatus(order.getStatus());
        Order savedOrder = orderRepository.save(o);
        return savedOrder;
    }
}

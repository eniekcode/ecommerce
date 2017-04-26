package com.ecommerce.core.customer;

import com.ecommerce.core.entity.Customer;
import com.ecommerce.core.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dguzik
 */
@Service
@Transactional
public class CustomerService {

    @Autowired CustomerRepository customerRepository;

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findOne(id);
    }

    public List<Order> getCustomerOrders(String email) {
        return customerRepository.getCustomerOrders(email);
    }

}

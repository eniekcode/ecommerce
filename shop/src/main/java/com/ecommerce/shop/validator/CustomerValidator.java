package com.ecommerce.shop.validator;

import com.ecommerce.core.customer.CustomerService;
import com.ecommerce.core.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by english on 5/7/17.
 */
@Component
public class CustomerValidator implements Validator {

    @Autowired
    private CustomerService custmoerService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Customer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Customer customer = (Customer) target;
        Customer customerByEmail = custmoerService.getCustomerByEmail(customer.getEmail());
        if(customerByEmail != null){
            errors.rejectValue("email", "error.exists", new Object[]{customer.getEmail()}, "Email "+customer.getEmail()+" already in use");
        }
    }
}

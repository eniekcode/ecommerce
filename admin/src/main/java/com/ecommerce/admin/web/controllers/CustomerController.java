package com.ecommerce.admin.web.controllers;

import com.ecommerce.admin.security.SecurityUtil;
import com.ecommerce.core.customer.CustomerService;
import com.ecommerce.core.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by dguzik
 */
@Controller
@Secured(SecurityUtil.MANAGE_CUSTOMERS)
public class CustomerController extends AdminBaseController {

    private static final String viewPrefix = "customers/";

    @Autowired private CustomerService customerService;

    @Override
    protected String getHeaderTitle()
    {
        return "Manage Customers";
    }

    @RequestMapping(value="/customers", method= RequestMethod.GET)
    public String listCustomers(Model model) {
        List<Customer> list = customerService.getAllCustomers();
        model.addAttribute("customers",list);
        return viewPrefix+"customers";
    }

    @RequestMapping(value="/customers/{id}", method=RequestMethod.GET)
    public String viewCustomerForm(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer",customer);
        return viewPrefix+"view_customer";
    }
}

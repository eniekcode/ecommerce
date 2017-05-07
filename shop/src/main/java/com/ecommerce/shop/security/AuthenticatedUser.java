package com.ecommerce.shop.security;

import com.ecommerce.core.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by english on 5/7/17.
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private Customer customer;

    public AuthenticatedUser(Customer customer)
    {
        super(customer.getEmail(), customer.getPassword(), getAuthorities(customer));
        this.customer = customer;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    private static Collection<? extends GrantedAuthority> getAuthorities(Customer customer)
    {
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        return authorities;
    }
}

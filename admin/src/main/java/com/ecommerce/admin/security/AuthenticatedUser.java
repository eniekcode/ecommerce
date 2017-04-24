package com.ecommerce.admin.security;

import com.ecommerce.core.entity.Permission;
import com.ecommerce.core.entity.Role;
import com.ecommerce.core.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dguzik
 */
public class AuthenticatedUser extends org.springframework.security.core.userdetails.User implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    public AuthenticatedUser(User user) {

        super(user.getEmail(), user.getPassword(), getAuthorities(user));
        this.user = user;
    }

    public User getUser() {

        return user;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {

        Set<String> roleAndPermissions = new HashSet<>();
        List<Role> roles = user.getRoles();

        for (Role role : roles) {

            roleAndPermissions.add(role.getName());
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                roleAndPermissions.add("ROLE_" + permission.getName());
            }
        }
        String[] roleNames = new String[roleAndPermissions.size()];
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
        return authorities;
    }
}

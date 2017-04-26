package com.ecommerce.core.security;

import com.ecommerce.core.entity.Permission;
import com.ecommerce.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dguzik
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}

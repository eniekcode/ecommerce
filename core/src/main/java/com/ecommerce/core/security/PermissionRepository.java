package com.ecommerce.core.security;

import com.ecommerce.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dguzik
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}

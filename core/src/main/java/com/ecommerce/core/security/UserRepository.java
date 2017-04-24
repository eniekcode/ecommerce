package com.ecommerce.core.security;

import com.ecommerce.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dguzik
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}

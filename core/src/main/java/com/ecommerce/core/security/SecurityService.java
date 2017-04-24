package com.ecommerce.core.security;

import com.ecommerce.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dguzik
 */
@Service
@Transactional
public class SecurityService {

    @Autowired UserRepository userRepository;

    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
//        return null;
    }
}

package com.ecommerce.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Created by english on 4/19/17.
 */

@SpringBootApplication(scanBasePackages = {"com.eccomerce"})
public class AdminApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(AdminApplication.class, args);
    }
}

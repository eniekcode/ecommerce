package com.ecommerce.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.ecommerce.shop.ShopApplication.BASE_PACAGE;

/**
 * Created by english on 4/19/17.
 */
@EntityScan(basePackages= {BASE_PACAGE})
@EnableJpaRepositories(basePackages= {BASE_PACAGE})
@SpringBootApplication(scanBasePackages = {BASE_PACAGE})
public class ShopApplication {

    public static final String BASE_PACAGE = "com.ecommerce";

    public static void main(String[] args)
    {
        SpringApplication.run(ShopApplication.class, args);
    }
}

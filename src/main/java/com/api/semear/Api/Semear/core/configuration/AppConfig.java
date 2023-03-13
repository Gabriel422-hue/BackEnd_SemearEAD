package com.api.semear.Api.Semear.core.configuration;

import com.api.semear.Api.Semear.domain.cart.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}


package com.usvoih.processing.validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationChainConfig {
    @Bean
    public Validator validator() {
        return nameValidator();
    }

    @Bean
    public NameValidator nameValidator() {
        return new NameValidator(addressValidator());
    }

    @Bean
    public AddressValidator addressValidator() {
        return new AddressValidator(null);
    }
}

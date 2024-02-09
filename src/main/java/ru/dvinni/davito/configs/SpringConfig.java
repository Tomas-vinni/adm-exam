package ru.dvinni.davito.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация Spring.
 */
@Configuration
public class SpringConfig {


    @Bean
    public String getDefaultPassword(@Value("${const.default.password}") String defaultPassword) {
        return defaultPassword;
    }
}

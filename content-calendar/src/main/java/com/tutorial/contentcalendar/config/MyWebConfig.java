package com.tutorial.contentcalendar.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 *  Configuration class for creating and configuring a RestTemplate bean.
 *
 * This class defines a Spring bean for RestTemplate, which is used for making HTTP requests in the application.
 * By declaring RestTemplate as a bean, it can be easily injected into other components.
 * This promotes reusability and maintains a consistent configuration for the HTTP client.
 */
@Configuration
public class MyWebConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}

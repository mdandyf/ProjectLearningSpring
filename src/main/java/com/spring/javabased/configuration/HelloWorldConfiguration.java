package com.spring.javabased.configuration;

import com.spring.model.HelloWorld;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
        @Bean
        public HelloWorld helloWorld() {
            return new HelloWorld();
        }
}

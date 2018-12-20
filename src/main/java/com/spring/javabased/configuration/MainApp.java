package com.spring.javabased.configuration;

import com.spring.model.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);

        HelloWorld obj = (HelloWorld) context.getBean(HelloWorld.class);
        obj.setMessage("Hello World!");
        obj.getMessage();
        obj.setMessage2("Hello World2!");
        obj.getMessage2();
    }
}

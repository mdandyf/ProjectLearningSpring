package com.spring.xmlbased.basic;

import com.spring.model.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        HelloWorld obj1 = (HelloWorld) context.getBean("helloWorld");
        obj1.setMessage("This is object 1");
        obj1.getMessage();

        HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld");
        obj2.getMessage();
    }
}

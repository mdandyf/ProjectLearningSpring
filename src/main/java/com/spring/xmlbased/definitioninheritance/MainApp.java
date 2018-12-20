package com.spring.xmlbased.definitioninheritance;

import com.spring.model.HelloIndia;
import com.spring.model.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("BeansDefinitionInheritance.xml");

        HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
        objA.getMessage();
        objA.getMessage2();

        HelloIndia objB = (HelloIndia) context.getBean("helloIndia");
        objB.getMessage1();
        objB.getMessage2();
        objB.getMessage3();
    }
}

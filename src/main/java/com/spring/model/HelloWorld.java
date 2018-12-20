package com.spring.model;

public class HelloWorld {

    private String message;
    private String message2;

    public void getMessage() {
       System.out.println("Your message is: " + message);
    }

    public void setMessage(String message) { this.message = message; }

    public void getMessage2() { System.out.println("Your message is: " + message2); }

    public void setMessage2(String message2) { this.message2 = message2; }

    public void init(){
        System.out.println("Bean is going through init.");
    }
    public void destroy() {
        System.out.println("Bean will destroy now.");
    }
}

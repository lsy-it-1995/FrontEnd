package com.lsy.spring;

import com.lsy.spring.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloworld = (HelloWorld) ioc.getBean("helloworld");
        helloworld.sayHello();
    }
}

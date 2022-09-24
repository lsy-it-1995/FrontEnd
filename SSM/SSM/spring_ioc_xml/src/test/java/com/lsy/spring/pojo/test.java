package com.lsy.spring.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {

    @Test
    public void testIOC(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring_ioc.xml");
        Student student = (Student) ioc.getBean("studentOne");
        System.out.println(student);
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class Alien {
    private int aid;
    private String name;
    private String tech;
    @Autowired
    @Qualifier("lap1")
    private Laptop laptop;

    public Laptop getLaptop() {
        return this.laptop;
    }
    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }


    public Alien() {
        super();
        System.out.println("Alien Constructor Creating...");
    }
    public int getAid() {
        return this.aid;
    }
    public void setAid(int aid) {
        this.aid = aid;
    }


    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }


    public String getTech() {
    	return this.tech;
    }
    public void setTech(String tech) {
    	this.tech = tech;
    }
    public void show(){
        System.out.println("Alien show");
        laptop.compile();
    }
}
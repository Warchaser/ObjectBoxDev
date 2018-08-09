package com.warchaser.objcetboxdev.nosql.entity;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ExampleEntity{

    /**
     * 必写空构造
     * */
    public ExampleEntity(){

    }

    public ExampleEntity(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Id
    public long id;

    public String name;

    public int age;

}

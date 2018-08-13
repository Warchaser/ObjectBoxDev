package com.warchaser.objcetboxdev.nosql.entity;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Uid;

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

    @Uid(2049970417808647494L)
    public int age;

}

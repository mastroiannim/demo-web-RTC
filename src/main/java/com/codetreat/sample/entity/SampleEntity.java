package com.codetreat.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//https://www.codetreat.com/spring-boot-data-jpa-hibernate-with-ms-sql-server-rest-crud-example/
 
@Entity
@Table(name = "Sample")
public class SampleEntity {
 
    @Id
    @Column(name = "id")
    Integer Id;
 
    @Column(name = "name")
    String name;
 
    @Column(name = "age")
    Integer age;
 
    public Integer getId() {
        return Id;
    }
 
    public void setId(Integer id) {
        Id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
 
}

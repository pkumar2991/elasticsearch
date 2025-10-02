package com.example.elastic_search_playground.sec02.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

@Mapping(mappingPath = "sec02/index-mapping.json")
@Document(indexName = "employees")
public class Employee {

    @Id
    private Integer id; // Elastic search not update this field, as it looks for field Id with String data type.
    private String name;
    private Integer age;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
}

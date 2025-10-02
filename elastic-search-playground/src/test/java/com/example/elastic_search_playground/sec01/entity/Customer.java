package com.example.elastic_search_playground.sec01.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "customers")
@Setting(settingPath = "sec01/index-setting.json")
@Mapping(mappingPath = "sec01/index-mapping.json")
public class Customer {

    @Id
    private String id;
    private String name;
    private String brand;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}

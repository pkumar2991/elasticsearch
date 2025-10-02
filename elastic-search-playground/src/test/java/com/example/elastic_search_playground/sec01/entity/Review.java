package com.example.elastic_search_playground.sec01.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "reviews")
@Setting(shards = 2, replicas = 2)
public class Review {

    @Id
    private String id;

    // getter and setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}

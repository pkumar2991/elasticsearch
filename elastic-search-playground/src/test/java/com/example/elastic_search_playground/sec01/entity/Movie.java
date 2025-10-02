package com.example.elastic_search_playground.sec01.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "movies")
public class Movie {

    @Id
    private String id;

    @Field(name = "customer",type = FieldType.Text)
    private String name;

    @Field(name = "genre",type = FieldType.Keyword)
    private String category;

    @Field(name = "rating",type = FieldType.Integer)
    private Integer rating;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}

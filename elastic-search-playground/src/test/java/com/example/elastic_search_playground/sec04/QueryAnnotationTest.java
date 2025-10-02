package com.example.elastic_search_playground.sec04;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.elastic_search_playground.AbstractTest;
import com.example.elastic_search_playground.sec04.entity.Article;
import com.example.elastic_search_playground.sec04.repository.ArticleRepository;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.type.TypeReference;

public class QueryAnnotationTest extends AbstractTest {

    @Autowired
    private ArticleRepository  repository;

    @BeforeAll
    public void setup(){
        var articles = this.readResource("sec04/articles.json", new TypeReference<List<Article>>() {
        });
        this.repository.saveAll(articles);
        Assertions.assertEquals(11, this.repository.count());
    }

    @Test
    public void searchTest(){
        var searchTerm = "spring seasen";
        var searchHit = this.repository.search(searchTerm);
        this.print().accept(searchHit.getSearchHits());
        Assertions.assertEquals(4, searchHit.getTotalHits());
    }
    
}

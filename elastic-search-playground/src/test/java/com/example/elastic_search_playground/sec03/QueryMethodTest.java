package com.example.elastic_search_playground.sec03;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;

import com.example.elastic_search_playground.AbstractTest;
import com.example.elastic_search_playground.sec03.entity.Product;
import com.example.elastic_search_playground.sec03.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;

public class QueryMethodTest extends AbstractTest{

    private static final Logger log  = LoggerFactory.getLogger(QueryMethodTest.class);

    @Autowired
    ProductRepository repository;

    @BeforeAll
    public void setup(){
        var products = this.readResource("sec03/products.json", new TypeReference<List<Product>>() {
        });
        repository.saveAll(products);
        log.info("Products read from file {}", products.size());
        Assertions.assertEquals(20, repository.count());
    }

    @Test
    public void findByCategory(){
        var searchHits = repository.findByCategory("Furniture");
        searchHits.forEach(this.print());
        Assertions.assertEquals(4, searchHits.getTotalHits());
    }

    @Test
    public void findByCategories(){
        var categories = List.of("Furniture", "Beauty");
        var searchHits = repository.findByCategoryIn(categories);
        searchHits.forEach(this.print());
        Assertions.assertEquals(8, searchHits.getTotalHits());
    }

    @Test
    public void findByCategoryAndBrand(){
        var searchHits = repository.findByCategoryAndBrand("Furniture", "Ikea");
        searchHits.forEach(this.print());
        Assertions.assertEquals(2, searchHits.getTotalHits());
    }

    @Test
    public void findByName(){ // AND Operator
        var searchHits = repository.findByName("Coffee Table");
        searchHits.forEach(this.print());
        Assertions.assertEquals(1, searchHits.getTotalHits());
    }

    @Test
    public void findByPriceLessThan(){
        var searchHits = repository.findByPriceLessThan(80);
        searchHits.forEach(this.print());
        Assertions.assertEquals(5, searchHits.getTotalHits());
    }

    @Test
    public void findByPriceBetween(){
        var searchHits = repository.findByPriceBetween(10, 120, Sort.by("price").ascending());
        searchHits.forEach(this.print());
        Assertions.assertEquals(8, searchHits.getTotalHits());
    }

    @Test
    public void findAllBySorting(){
        var iterable = repository.findAll(Sort.by("quantity").descending());
        iterable.forEach(this.print());
        Assertions.assertEquals(20, Streamable.of(iterable).toList().size());
    }

     @Test
    public void findByCategoryWithPagination(){
        var page = repository.findByCategory("Electronics", PageRequest.of(0, 2));
         // page 0, size 2
        page.forEach(this.print());
        Assertions.assertEquals(0, page.getNumber());
        Assertions.assertEquals(12, page.getTotalElements());
    }

}

package com.example.elastic_search_playground.sec01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import com.example.elastic_search_playground.AbstractTest;
import com.example.elastic_search_playground.sec01.entity.Customer;
import com.example.elastic_search_playground.sec01.entity.Movie;
import com.example.elastic_search_playground.sec01.entity.Review;

public class IndexOperationsTest extends AbstractTest {

    private Logger log = LoggerFactory.getLogger(IndexOperationsTest.class);


    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    @Test
    public void createIndexTest() {
        var indexOperations = this.elasticsearchOperations.indexOps(IndexCoordinates.of("albums"));
        Assertions.assertTrue(indexOperations.create());
        log.info("Settings: {}", indexOperations.getSettings());
        verifyCreateIndex(indexOperations, 1, 1);
    }

    @Test
    public void createIndexTestWithIndex() {
        var indexOperations = this.elasticsearchOperations.indexOps(Review.class);
        Assertions.assertTrue(indexOperations.create());
        log.info("Settings: {}", indexOperations.getSettings());
        verifyCreateIndex(indexOperations, 2, 2);
    }

     @Test
    public void createIndexTestWithIndexForMovies() {
        var indexOperations = this.elasticsearchOperations.indexOps(Movie.class);
        Assertions.assertTrue(indexOperations.createWithMapping());
        log.info("Settings: {}", indexOperations.getSettings());
        log.info("Settings: {}", indexOperations.getMapping());
        verifyCreateIndex(indexOperations, 1, 1);
    }

    @Test
    public void createIndexTestWithIndexAndMapping() {
        var indexOperations = this.elasticsearchOperations.indexOps(Customer.class);
        Assertions.assertTrue(indexOperations.createWithMapping());
        log.info("Settings: {}", indexOperations.getSettings());
        verifyCreateIndex(indexOperations, 3, 0);
    }

    private void verifyCreateIndex(IndexOperations indexOp, int expectedShards, int expectedReplicas) {
        var settings = indexOp.getSettings();
        Assertions.assertEquals(String.valueOf(expectedShards), settings.get("index.number_of_shards"));
        Assertions.assertEquals(String.valueOf(expectedReplicas), settings.get("index.number_of_replicas"));


        Assertions.assertTrue(indexOp.delete());
    }

}

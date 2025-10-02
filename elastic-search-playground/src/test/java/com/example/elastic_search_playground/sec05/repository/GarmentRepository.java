package com.example.elastic_search_playground.sec05.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elastic_search_playground.sec05.entity.Garment;

@Repository
public interface GarmentRepository extends ElasticsearchRepository<Garment, String> {
}

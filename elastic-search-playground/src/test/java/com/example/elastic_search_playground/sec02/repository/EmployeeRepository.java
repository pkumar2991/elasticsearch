package com.example.elastic_search_playground.sec02.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elastic_search_playground.sec02.entity.Employee;

@Repository
public interface EmployeeRepository extends ElasticsearchRepository<Employee,Integer>{

}

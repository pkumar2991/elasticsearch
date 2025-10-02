package com.example.elastic_search_playground;

import org.springframework.boot.SpringApplication;

public class TestElasticSearchPlaygroundApplication {
    public static void main(String[] args) {
		SpringApplication.from(ElasticSearchPlaygroundApplication::main).with(TestcontainersConfiguration.class).run(args);
	}
}

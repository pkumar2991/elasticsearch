package com.example.elastic_search_playground.sec04.repository;

import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.elastic_search_playground.sec04.entity.Article;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    @Query("""
            {
                "multi_match": {
                    "query":"?0",
                    "fields": ["title^3", "body"],
                    "fuzziness": "1",
                    "operator": "AND",
                    "type": "best_fields",
                    "tie_breaker": 0.7
                }
            }
        """)
    @Highlight(fields ={
        @HighlightField(name = "title")
    }, parameters = @HighlightParameters(preTags = "<b>", postTags = "</b>"))
    SearchHits<Article> search(String searchTerm);

}

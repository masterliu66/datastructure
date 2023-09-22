package com.example.code.test.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class ElasticSearchTest {

    public static void main(String[] args) throws IOException {

        RestClient httpClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        ElasticsearchTransport transport = new RestClientTransport(httpClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);

        Query query = MatchQuery.of(m -> m
                .field("name")
                .query("张三")
        )._toQuery();

//        TermsQuery.of(f -> f.terms(t -> t.))

        SearchResponse<Object> response = client.search(s ->
            s.index("idx_test").query(q -> q.bool(b -> b.must(query))),
            Object.class
        );

        System.out.println(response);

        httpClient.close();
    }

}

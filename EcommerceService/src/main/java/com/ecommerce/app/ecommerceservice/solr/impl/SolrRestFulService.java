package com.ecommerce.app.ecommerceservice.solr.impl;

import com.ecommerce.app.ecommerceservice.solr.HttpRestFulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class SolrRestFulService implements HttpRestFulService {

    private  WebClient webClient = WebClient.builder().baseUrl("http://localhost:8983/solr/techproducts").build();


    @Override
    public String basicSearch(Map<String, String> query) {
        String path=queryBuilder("/select",query);
        return makeRestCall(path).block();
    }

    @Override
    public String autoSuggestSearch(Map<String,String> suggest) {
        suggest.put("suggest.build","true");
        suggest.put("suggest.dictionary","mySuggester");
        String query=queryBuilder("/suggest",suggest);
        System.out.println("Qyery -"+query);
        return makeRestCall(query).block();
    }


    public Mono<String> makeRestCall(String path) {
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(String.class);
    }

    private String queryBuilder(String path,Map<String,String> allParams){
        StringBuilder queryStringBuilder = new StringBuilder();

        if (allParams != null) {
            for (Map.Entry<String, String> entry : allParams.entrySet()) {
                if (queryStringBuilder.length() > 0) {
                    queryStringBuilder.append("&");
                }
                queryStringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }

        return path+"?"+queryStringBuilder.toString();
    }


}

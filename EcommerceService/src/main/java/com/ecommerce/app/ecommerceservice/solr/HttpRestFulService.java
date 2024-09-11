package com.ecommerce.app.ecommerceservice.solr;

import java.util.Map;

public interface HttpRestFulService {


    String basicSearch(Map<String,String> query);

    String autoSuggestSearch(Map<String,String> query);

    /*
    public String basicSearch(String query);

    public String facetedSearch(String query);

    public String sortAndRankSearch(String query);

    public String autoSuggestSearch(String query);
    */
}

package com.ecommerce.solrsearch.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface HttpRestFulService {

    String basicSearch(Map<String,String> query);

    String autoSuggestSearch(Map<String,String> query);
}

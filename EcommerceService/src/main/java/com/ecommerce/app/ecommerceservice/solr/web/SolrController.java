package com.ecommerce.app.ecommerceservice.solr.web;

import com.ecommerce.app.ecommerceservice.solr.HttpRestFulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/solr/api")
public class SolrController {

    @Autowired
    HttpRestFulService httpService;
    @RequestMapping(path = "/")
    public String welcome(){
        return "welcome solr api";
    }
    @RequestMapping(path = "/search")
    public String basicSearch(@RequestParam Map<String,String> q){
        return httpService.basicSearch(q);
    }

    @RequestMapping(path = "/autocompletion")
    public String autoCompleteSearch(@RequestParam(required = false) Map<String,String> params){
        return httpService.autoSuggestSearch(params);
    }


}

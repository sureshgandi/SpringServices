package com.spring.mysqldb.springdbservice.web;

import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String welcome(){
        return "Application Active";
    }


}

package com.ecommerce.app.ecommerceservice.web;

import com.ecommerce.app.ecommerceservice.model.Item;
import com.ecommerce.app.ecommerceservice.service.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WebController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "/item/{id}",method = RequestMethod.GET)
    public Optional<Item> findItemById(@PathVariable Long id){
        return itemRepository.findById(id);
    }

    @RequestMapping(value = "/item",method = RequestMethod.POST)
    public Item createItem(@Validated @RequestBody Item item){
        return itemRepository.save(item);
    }

    @RequestMapping(value = "/items",method = RequestMethod.GET)
    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

}

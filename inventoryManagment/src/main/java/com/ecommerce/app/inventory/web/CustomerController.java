package com.ecommerce.app.inventory.web;


import com.ecommerce.app.inventory.dynomodb.model.Customer;
import com.ecommerce.app.inventory.dynomodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;

import java.util.List;

@RestController
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping("/")
    public String getWelcome(){
        return "Welcome";
    }

    @RequestMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id){
        return customerRepository.getById(id);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createCustomer(@RequestBody Customer customer){
        customerRepository.saveCustomer(customer);
        return "Successfully Updated";
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT)
    public Customer updateCustomer( @RequestBody Customer cust){
        return customerRepository.updateCustomer(cust);
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public Customer searchCustomerByName(@RequestParam String name){
        System.out.println("Search By Name=> "+name);
        return customerRepository.searchNyName(name);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Customer> getCustomerslist(){
        return customerRepository.fetchAllCustomers();
    }

}

package com.qt.neo4j.controller;

import com.qt.neo4j.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/countCustomerNums")
    public int countCustomerNums(){
        return customerService.countCustomer();
    }

    @RequestMapping("/getCustomerByCustomerId")
    public HashMap<String,Object> getCustomerByCustomerId(@RequestParam("customerId")String customerId){
        return customerService.getCustomerByCustomerId(customerId);
    }

    @RequestMapping("/getAllCustomer")
    public HashMap<String,Object> getAllCustomer(){
        return customerService.getAllCustomer();
    }
}

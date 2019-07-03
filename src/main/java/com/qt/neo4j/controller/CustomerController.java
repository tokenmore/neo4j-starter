package com.qt.neo4j.controller;

import com.qt.neo4j.dao.CustomerRepsitory;
import com.qt.neo4j.entitiy.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepsitory customerRepsitory;

    @RequestMapping("/countCustomerNums")
    public int countCustomerNums(){
        return customerRepsitory.countAll();
    }

    @RequestMapping("/getCustomerByCustomerId")
    public Customer getCustomerByCustomerId(@RequestParam("customerId")String customerId){
        return customerRepsitory.getCustomerByCustomerId(customerId);
    }

    @RequestMapping("/getAllCustomer")
    public List<Customer> getAllCustomer(){
        return customerRepsitory.getAllCustomer();
    }
}

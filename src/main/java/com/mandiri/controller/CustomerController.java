package com.mandiri.controller;

import com.mandiri.entity.Customer;
import com.mandiri.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/registration")
    public void createNewCustomer(@RequestBody Customer customer){
        customerService.createNewCustomer(customer);
    }

    @GetMapping("/someResources")
    public String doSomething(){
        return "someResources Accessed";
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerService.findAll();
    }
}

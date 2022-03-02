package com.mandiri.service;

import com.mandiri.entity.Customer;
import com.mandiri.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceDBImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void createNewCustomer(Customer customer) {
        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void removeCustomer(String id) {

    }
}

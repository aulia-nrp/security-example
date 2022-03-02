package com.mandiri.repository;

import com.mandiri.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    public Optional<Customer> findCustomerByUserName(String username);
}

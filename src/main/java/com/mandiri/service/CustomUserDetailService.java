package com.mandiri.service;

import com.mandiri.dto.UserDetailsImpl;
import com.mandiri.entity.Customer;
import com.mandiri.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!customerRepository.findCustomerByUserName(username).isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
        Customer customer = customerRepository.findCustomerByUserName(username).get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserDetails userDetails = new UserDetailsImpl(customer.getUserName(), customer.getPassword(), authorities);

        return userDetails;
    }
}

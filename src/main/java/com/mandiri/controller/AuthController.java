package com.mandiri.controller;

import com.mandiri.dto.LoginForm;
import com.mandiri.security.JWTokenUtil;
import com.mandiri.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    JWTokenUtil jwTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @GetMapping("/login")
    public String login(@RequestBody LoginForm loginForm){
        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(loginForm.getUsername(),loginForm.getPassword());
        authenticationManager.authenticate(upat);
        UserDetails userDetails = customUserDetailService.loadUserByUsername(loginForm.getUsername());
        String token = jwTokenUtil.generateToken(userDetails);
        return token;
    }
}

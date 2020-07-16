package com.reactgenova.springmicroservice.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * UserController
 */
@RestController
@RequestMapping(path = "api/client")
public class UserClient {

    @Autowired
    RestTemplate restTemplate;
    
    @GetMapping
    public List<User> getUsers() {
        String userResourceUrl = "http://training-service/api/user";
        User[] response = restTemplate.getForObject(userResourceUrl, User[].class);
        return Arrays.asList(response);
    }
}
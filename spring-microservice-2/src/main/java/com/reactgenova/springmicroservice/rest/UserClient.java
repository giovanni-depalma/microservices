package com.reactgenova.springmicroservice.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * UserController
 */
@RestController
@RequestMapping(path = "api/client")
public class UserClient {

    @GetMapping
    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        String userResourceUrl = "http://spring-microservice:9000/api/user";
        User[] response = restTemplate.getForObject(userResourceUrl, User[].class);
        return Arrays.asList(response);
    }
}
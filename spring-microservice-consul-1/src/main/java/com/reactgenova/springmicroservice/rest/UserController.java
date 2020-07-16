package com.reactgenova.springmicroservice.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * UserController
 */
@RestController
@RequestMapping(path = "api/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getUsers() {
        logger.info("getUsers");
        return IntStream.range(1, 10)
                .mapToObj(i -> new User("username_" + i, "fistname_" + i, "lastName_" + 1, "description_" + i))
                .collect(Collectors.toList());
    }
}
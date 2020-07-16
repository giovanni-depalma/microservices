package com.reactgenova.springmicroservice.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record User(@JsonProperty("userName") String userName, @JsonProperty("firstName") String firstName,
        @JsonProperty("lastName") String lastName, @JsonProperty("description") String description) {

}
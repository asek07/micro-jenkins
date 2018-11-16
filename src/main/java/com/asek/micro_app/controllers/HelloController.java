package com.asek.micro_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping
    public String greeting() {
        return "This has changed, wow!";
    }

    @GetMapping(value = "/{userName}")
    public String helloUser(@PathVariable String userName) {
        return "Hello there " + userName;
    }

}

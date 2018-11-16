package com.asek.micro_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping
    public String greeting() {
        return "Hello from the first method in the controller!";
    }

}

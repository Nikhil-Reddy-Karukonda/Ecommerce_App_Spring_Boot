package com.ecommerce.springbootrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootRestApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRestApiApplication.class, args);
    }

    @GetMapping("/")
    public String greet(){
        return "Get call";
    }

}

package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MainController {

    @GetMapping(value = "/")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userInfo";
    }

    @GetMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

}
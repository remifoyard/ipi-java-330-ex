package com.ipiecoles.java.java320.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController extends NavBarController{

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        super.index(model);
        return "index";
    }

}
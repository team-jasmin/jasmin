package io.github.jasmin.controller;

import io.github.jasmin.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private TestService testService;

    @GetMapping("")
    public String test(){
        return "test man~";
    }

    @GetMapping("/test")
    public Map<String, String> testMethod(){
        Map<String, String> res = this.testService.getTest();

        return res;
    }
}

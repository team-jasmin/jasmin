package io.github.jasmin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestService {

    @GetMapping("/json")
    public Map<String, String> getTest(){
        Map<String, String> res = new HashMap<>();
        res.put("test", "hihi");

        return res;
    }
}

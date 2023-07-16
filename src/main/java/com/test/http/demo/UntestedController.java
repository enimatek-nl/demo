package com.test.http.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UntestedController {

    @GetMapping(value = "/untest")
    public Post getUntestedData() {
        return new Post(1, 1, "", "");
    }

}

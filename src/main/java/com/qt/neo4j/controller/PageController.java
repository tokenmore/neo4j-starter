package com.qt.neo4j.controller;

import com.qt.neo4j.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @RequestMapping("/d3")
    public String goToD3(){
        return "d32";
    }
}

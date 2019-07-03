package com.qt.neo4j.controller;

import com.qt.neo4j.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private Neo4jService neo4jService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/getallnodeLabels")
    @ResponseBody
    public List<Object> getAllNodes(Model model){
        List<Object> labels = neo4jService.getAllNodeLabels();
        model.addAttribute("labels",labels);
        return labels;
    }

    @RequestMapping("/d3")
    public String goToD3(){
        return "d32";
    }
}

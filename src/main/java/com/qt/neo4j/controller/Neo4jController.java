package com.qt.neo4j.controller;

import com.qt.neo4j.service.Neo4jService;
import com.qt.neo4j.service.Neo4jService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Neo4jController {
    @Autowired
    private Neo4jService neo4jService;
    @Autowired
    private Neo4jService1 neo4jService1;

    @RequestMapping("/getAllFullRelations")
    public HashMap<String,Object> getAllFullRelations(){
        return neo4jService1.getAllFullRelations();
    }

    @RequestMapping("/getFullRelationByCaseId")
    public HashMap<String,Object> getFullRelationByCaseId(@RequestParam("caseId") String caseId){
        System.out.println(caseId);
        return neo4jService1.getFullRelationByCaseId(caseId);
    }

    @RequestMapping("/getFullRelationByTelId/{telId}")
    public HashMap<String,Object> getFullRelationByTelId(@PathVariable("telId") String telId){
        return neo4jService1.getFullRelationByTelId(telId);
    }

    @RequestMapping("/getFullRelationByHosId/{hosId}")
    public HashMap<String,Object> getFullRelationByHosId(@PathVariable("hosId") String hosId){
        return neo4jService.getFullRelationByHosId(hosId);
    }

    @RequestMapping("/getFullRelationByEmpId/{empId}")
    public HashMap<String,Object> getFullRelationByEmpId(@PathVariable("empId") String empId){
        return neo4jService1.getFullRelationByEmpId(empId);
    }

    @RequestMapping("/getFullRelationByCustomerId/{-customerId}")
    public HashMap<String,Object> getFullRelationByCustomerId(@PathVariable("customerId") String customerId){
        return neo4jService1.getFullRelationByCustomerId(customerId);
    }
}

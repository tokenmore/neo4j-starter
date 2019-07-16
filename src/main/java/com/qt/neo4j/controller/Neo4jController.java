package com.qt.neo4j.controller;

import com.qt.neo4j.service.Neo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class Neo4jController {
    @Autowired
    private Neo4jService neo4jService;

    @RequestMapping("/getAllFullRelations")
    public HashMap<String,Object> getAllFullRelations(){
        return neo4jService.getAllFullRelations();
    }

    @RequestMapping("/getFullRelationByCaseId/{caseId}")
    public HashMap<String,Object> getFullRelationByCaseId(@PathVariable("caseId") String caseId){
        return neo4jService.getFullRelationByCaseId(caseId);
    }

    @RequestMapping("/getFullRelationByTelId/{telId}")
    public HashMap<String,Object> getFullRelationByTelId(@PathVariable("telId") String telId){
        return neo4jService.getFullRelationByTelId(telId);
    }

    @RequestMapping("/getFullRelationByHosId/{hosId}")
    public HashMap<String,Object> getFullRelationByHosId(@PathVariable("hosId") String hosId){
        return neo4jService.getFullRelationByHosId(hosId);
    }

    @RequestMapping("/getFullRelationByEmpId/{empId}")
    public HashMap<String,Object> getFullRelationByEmpId(@PathVariable("empId") String empId){
        return neo4jService.getFullRelationByEmpId(empId);
    }

    @RequestMapping("/getFullRelationByCustomerId/{customerId}")
    public HashMap<String,Object> getFullRelationByCustomerId(@PathVariable("customerId") String customerId){
        return neo4jService.getFullRelationByCustomerId(customerId);
    }
}
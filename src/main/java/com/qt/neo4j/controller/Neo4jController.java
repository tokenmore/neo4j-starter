package com.qt.neo4j.controller;

import com.qt.neo4j.service.Neo4jService;
import com.qt.neo4j.service.Neo4jService1;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @RequestMapping("/getFullRelationByCaseId/{caseId}")
//    public HashMap<String,Object> getFullRelationByCaseId(@PathVariable("caseId") String caseId){
//        System.out.println(caseId);
//        return neo4jService1.getFullRelationByCaseId(caseId);
//    }

//    @RequestMapping("/getFullRelationByTelId/{telId}")
//    public HashMap<String,Object> getFullRelationByTelId(@PathVariable("telId") String telId){
//        return neo4jService1.getFullRelationByTelId(telId);
//    }

    @RequestMapping("/getFullRelationByHosId")
    public HashMap<String,Object> getFullRelationByHosId(@RequestParam("hosId") String hosId){
        return neo4jService.getFullRelationByHosId(hosId);
    }

    @RequestMapping("/getFullRelationByEmpId")
    public HashMap<String,Object> getFullRelationByEmpId(@RequestParam("empId") String empId){
        return neo4jService1.getFullRelationByEmpId(empId);
    }

    @RequestMapping("/getFullRelationByCustomerId")
    public HashMap<String,Object> getFullRelationByCustomerId(@RequestParam("customerId") String customerId){
        return neo4jService1.getFullRelationByCustomerId(customerId);
    }

    @RequestMapping("/getFullRelationBycustomerName")
    public HashMap<String,Object> getFullRelationBycustomerName(@RequestParam("customerName") String customerName){
        return neo4jService1.getFullRelationByCustomerName(customerName);
    }
}

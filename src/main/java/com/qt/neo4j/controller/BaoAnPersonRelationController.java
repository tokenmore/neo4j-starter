package com.qt.neo4j.controller;

import com.qt.neo4j.service.BaoAnPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BaoAnPersonRelationController {
    @Autowired
    private BaoAnPersonService baoAnPersonService;

    @RequestMapping("/countAllBaoAn")
    public int countAllBaoAnPersonRelation(){
        return baoAnPersonService.countAllBaoAnPersonRelation();
    }

    @RequestMapping("/findAllBaoAnRelation")
    public HashMap<String, Object> findAllBaoAnRelation(){
        return baoAnPersonService.getAllBaoAnPersonRelation();
    }

    @RequestMapping("/findBaoAnRelationByCaseId")
    public HashMap<String, Object> findBaoAnRelationByCustomerId(@RequestParam("customerId") String customerId){
        return baoAnPersonService.getBaoAnPersonRelationByCustomerId(customerId);
    }

    @RequestMapping("/findBaoAnRelationByCustomerId/{caseId}")
    public HashMap<String, Object> findBaoAnRelationByCaseId(@PathVariable("caseId") String caseId){
        return baoAnPersonService.getBaoAnPersonRelationByCaseId(caseId);
    }
}

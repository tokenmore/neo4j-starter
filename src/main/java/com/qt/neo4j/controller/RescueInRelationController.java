package com.qt.neo4j.controller;

import com.qt.neo4j.dao.RescueRespository;
import com.qt.neo4j.entitiy.relation.RescueInRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RescueInRelationController {

    @Autowired
    private RescueRespository rescueRespository;

    @RequestMapping("/findAllRescueRelatin")
    public List<RescueInRelation> findAllRescueRelatin(){
        return rescueRespository.findAllRescue();
    }

    @RequestMapping("/findRescueRelationByCaseId")
    public List<RescueInRelation> findRescueRelationByCaseId(@RequestParam("caseId")String caseId){
        return rescueRespository.findRescueInRelationByCaseId(caseId);
    }
}

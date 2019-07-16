package com.qt.neo4j.controller;

import com.qt.neo4j.service.RescureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RescueInRelationController {

    @Autowired
    private RescureService rescureService;

    @RequestMapping("/countRescueRelations")
    public int countRescueRelations(){
        return rescureService.countAllRescue();
    }

    @RequestMapping("/findAllRescueRelatin")
    public HashMap<String, Object> findAllRescueRelatin(){
        return rescureService.getAllRescueRelations();
    }

    @RequestMapping("/findRescueRelationByCaseId")
    public HashMap<String, Object> findRescueRelationByCaseId(@RequestParam("caseId")String caseId){
        return rescureService.getRescueRelationByCaseId(caseId);
    }
    @RequestMapping("/findRescueRelationByHosId")
    public HashMap<String, Object> findRescueRelationByhosId(@RequestParam("hosId")String hosId){
        return rescureService.getRescueRelationByCaseId(hosId);
    }
}

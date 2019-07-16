package com.qt.neo4j.controller;

import com.qt.neo4j.service.TouBaoRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TouBaoPersonRelationController {
    @Autowired
    private TouBaoRelationService touBaoRelationService;

    @RequestMapping("/countAllTouBaoRelation")
    public int countAllTouBaoRelation(){
        return touBaoRelationService.countTouBaoRelations();
    }

    @RequestMapping("/findAllTouBaoRelation")
    public HashMap<String, Object> findAllTouBaoRelation(){
        return touBaoRelationService.getAllTouBaoRelations();
    }

    @RequestMapping("/findAllTouBaoRelationByCustomerId")
    public HashMap<String,Object> findAllTouBaoRelationByCustomerId(@RequestParam("customerId")String customerId){
        return touBaoRelationService.getATouBaoRelationByCustomerId(customerId);
    }

    @RequestMapping("/findAllTouBaoRelationByCaseId")
    public HashMap<String,Object> findAllTouBaoRelationByCaseId(@RequestParam("caseId")String caseId){
        return touBaoRelationService.getATouBaoRelationByCaseId(caseId);
    }
}

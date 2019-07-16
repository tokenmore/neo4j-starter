package com.qt.neo4j.controller;

import com.qt.neo4j.service.LingKuanRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LingKuanPersonRelationControlelr {
    @Autowired
    private LingKuanRelationService lingKuanRelationService;

    @RequestMapping("/countAllLingkuanRelation")
    public int countAllLingkuanRelation(){
        return lingKuanRelationService.countLingKuanRelations();
    }

    @RequestMapping("/getaAllLingKuanRelations")
    public HashMap<String, Object> getaAllLingKuanRelations(){
        return lingKuanRelationService.getAllLingKuanRelations();
    }

    @RequestMapping("/findLingKuanRelationByCustomerId")
    public HashMap<String, Object> findLingKuanRelationByCustomerId(@RequestParam("customerId") String customerId){
        return lingKuanRelationService.getLingKuanRelationByCustomerId(customerId);
    }

    @RequestMapping("/findLingKuanRelationByCaseId")
    public HashMap<String, Object> findLingKuanRelationByCaseId(@RequestParam("caseId") String caseId){
        return lingKuanRelationService.getLingKuanRelationByCaseId(caseId);
    }
 }

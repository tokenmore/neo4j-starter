package com.qt.neo4j.controller;

import com.qt.neo4j.service.LingKuanTeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LingKuanTelRelationController {
    @Autowired
    private LingKuanTeleService lingKuanTeleService;

    @RequestMapping("/countALlLingKuanRelations")
    public int countAllLIngKUanRelations(){
        return lingKuanTeleService.countAllLingKuanTeleRelations();
    }

    @RequestMapping("/getAllLingKuanTelRelation")
    public HashMap<String, Object> getAllLingKuanTelRelation(){
        return lingKuanTeleService.getAllLingKuanTeleRelations();
    }

    @RequestMapping("/getLingKuanTelRelationByCaseId")
    public HashMap<String, Object> getLingKuanTelRelationByCaseId(@RequestParam("caseId") String caseId){
        return lingKuanTeleService.getLingKuanTeleRelationByCaseId(caseId);
    }

    @RequestMapping("/getLingKuanTelRelationByTelId")
    public HashMap<String, Object> getLingKuanTelRelationByTelId(@RequestParam("telId") String telId){
        return lingKuanTeleService.getLingKuanTeleRelationByTelId(telId);
    }
}

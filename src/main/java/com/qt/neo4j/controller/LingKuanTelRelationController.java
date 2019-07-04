package com.qt.neo4j.controller;

import com.qt.neo4j.dao.LingKuanTelRelationRepsitory;
import com.qt.neo4j.entitiy.relation.LingKuanTelRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LingKuanTelRelationController {
    @Autowired
    private LingKuanTelRelationRepsitory lingKuanTelRelationRepsitory;

    @RequestMapping("/getAllLingKuanTelRelation")
    public List<LingKuanTelRelation> getAllLingKuanTelRelation(){
        return lingKuanTelRelationRepsitory.getAllLingTelKuanRelations();
    }

    @RequestMapping("/getLingKuanTelRelationByCaseId")
    public List<LingKuanTelRelation> getLingKuanTelRelationByCaseId(@RequestParam("caseId") String caseId){
        return lingKuanTelRelationRepsitory.getLingKuanTelRelationByCaseId(caseId);
    }
}

package com.qt.neo4j.controller;

import com.qt.neo4j.dao.LingKuanRelationRepsitory;
import com.qt.neo4j.entitiy.LingKuanRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LingKuanPersonRelationControlelr {
    @Autowired
    private LingKuanRelationRepsitory lingKuanRelationRepsitory;

    @RequestMapping("/countAllLingkuanRelation")
    public int countAllLingkuanRelation(){
        return lingKuanRelationRepsitory.countAllLingKuanRelations();
    }

    @RequestMapping("/findLingKuanRelationByCustomerId")
    public List<LingKuanRelation> findLingKuanRelationByCustomerId(@RequestParam("customerId") String customerId){
        return lingKuanRelationRepsitory.findLingKuanRelationByCustomerId(customerId);
    }
 }

package com.qt.neo4j.controller;

import com.qt.neo4j.dao.BaoAnTelRelationRepsitory;
import com.qt.neo4j.entitiy.relation.BaoAnTelRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaoAnTelRelationCotroller {
    @Autowired
    private BaoAnTelRelationRepsitory baoAnTelRelationRepsitory;

    @RequestMapping("/baoanTelCount")
    public int baoanTelCount(){
        return baoAnTelRelationRepsitory.countAllBaoAnTelRelations();
    }

    @RequestMapping("/getAllBaoAnTelRelation")
    public List<BaoAnTelRelation> getAllBaoAnTel(){
        return baoAnTelRelationRepsitory.getAllTelRelation();
    }

    @RequestMapping("/getBaoAnTelRelationByAccId")
    public List<BaoAnTelRelation> getBaoAnTelRelationByAccId(@RequestParam("accId") String accId){
        return baoAnTelRelationRepsitory.getBaoAnTelRelationBycaseId(accId);
    }
 }

package com.qt.neo4j.controller;

import com.qt.neo4j.dao.BaoAnRelationRepsitory;
import com.qt.neo4j.entitiy.relation.BaoAnRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaoAnPersonRelationController {
    @Autowired
    private BaoAnRelationRepsitory baoAnRelationRepsitory;

    @RequestMapping("/countAllBaoAn")
    public int countAllBaoAn(){
        return baoAnRelationRepsitory.countAllBaoAnRelations();
    }

    @RequestMapping("/findAllBaoAnRelation")
    public List<BaoAnRelation> findAllBaoAnRelation(){
        return baoAnRelationRepsitory.findAllBaoAnRelation();
    }

    @RequestMapping("/findBaoAnRelationByCustomerId")
    public List<BaoAnRelation> findBaoAnRelationByCustomerId(@RequestParam("customerId") String customerId){
        return baoAnRelationRepsitory.findBaoAnRelationByCustomerId(customerId);
    }
}

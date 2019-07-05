package com.qt.neo4j.controller;

import com.qt.neo4j.dao.BussinessBelongRepsitory;
import com.qt.neo4j.entitiy.relation.BussinessBelong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BussinessBelongController {
    @Autowired
    private BussinessBelongRepsitory bussinessBelongRepsitory;

    @RequestMapping("/findAll   BussinessBeLongsBycaseId/{caseId}")
    public List<BussinessBelong> findAllBussinessBeLongsBycaseId(@PathVariable("caseId") String caseId){
        List<BussinessBelong> allBussinessBeLongsBycaseId = bussinessBelongRepsitory.findAllBussinessBeLongsBycaseId(caseId);
        System.out.println(caseId);
        return  allBussinessBeLongsBycaseId;
    }
}

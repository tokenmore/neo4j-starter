package com.qt.neo4j.controller;

import com.qt.neo4j.service.BaoAnTeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BaoAnTelRelationCotroller {
    @Autowired
    private BaoAnTeleService teleService;

    @RequestMapping("/baoanTelCount")
    public int baoanTelCount(){
        return  teleService.countAllBanAnRelation();
    }

    @RequestMapping("/getAllBaoAnTelRelation")
    public HashMap<String, Object> getAllBaoAnTel(){
        return teleService.getAllBaoAnTeleRelation();
    }

    @RequestMapping("/getBaoAnTelRelationByAccId/{accId}")
    public HashMap<String, Object> getBaoAnTelRelationByAccId(@PathVariable("accId") String accId){
        return teleService.getBaoAnTeleRelationByCaseId(accId);
    }
 }

package com.qt.neo4j.controller;

import com.qt.neo4j.service.BeiBaoPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BeiBaoPersonRelationController {
    @Autowired
    private BeiBaoPersonService beiBaoPersonService;

    @RequestMapping("/countAllBeiBao")
    public int countAllBeiBao(){
        return beiBaoPersonService.countBeiBaoPerson();
    }

    @RequestMapping("/findBeiBaoRelationByCustomerId/{customerId}")
    public HashMap<String, Object> findBeiBaoRelationByCustomerId(@PathVariable("customerId") String customerId){
        return beiBaoPersonService.getBeiBaoPersonByCustomerId(customerId);
    }
    @RequestMapping("/findBeiBaoRelationByCaseId/{caseId}")
    public HashMap<String, Object> findBeiBaoRelationByCaseId(@PathVariable("caseId") String caseId){
        return beiBaoPersonService.getBeiBaoPersonByCustomerId(caseId);
    }
    @RequestMapping("/findAllBeiBaoRelation")
    public HashMap<String, Object> findBeiBaoRelation(){
        return beiBaoPersonService.getAllBeiBaoPerson();
    }
}

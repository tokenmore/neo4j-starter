package com.qt.neo4j.controller;

import com.qt.neo4j.dao.BeiBaoRelationRepsitory;
import com.qt.neo4j.entitiy.BeiBaoRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeiBaoPersonRelationController {
    @Autowired
    private BeiBaoRelationRepsitory beiBaoRelationRepsitory;

    @RequestMapping("/countAllBeiBao")
    public int countAllBeiBao(){
        return beiBaoRelationRepsitory.countAllBeiBaoRelations();
    }

    @RequestMapping("/findBeiBaoRelationByCustomerId")
    public List<BeiBaoRelation> findBeiBaoRelationByCustomerId(@RequestParam("customerId") String customerId){
        return beiBaoRelationRepsitory.findBeiBaoRelationByCustomerId(customerId);
    }
}

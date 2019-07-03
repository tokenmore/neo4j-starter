package com.qt.neo4j.controller;

import com.qt.neo4j.dao.UseTelRepsitory;
import com.qt.neo4j.entitiy.UserTelRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UseTelRelationController {
    @Autowired
    private UseTelRepsitory useTelRepsitory;

    @RequestMapping("/countAllTelRelations")
    public int countAllRelations(){
        return useTelRepsitory.countAllTelRelations();
    }

    @RequestMapping("/findUserTelRelationByCustomerId")
    public List<UserTelRelation> findUserTelRelationByCustomerId(@RequestParam("customerId") String customerId){
        return useTelRepsitory.findUserTelRelationByCustomerId(customerId);
    }


}

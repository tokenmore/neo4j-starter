package com.qt.neo4j.controller;

import com.qt.neo4j.service.UseTelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UseTelRelationController {
    @Autowired
    private UseTelService useTelService;

    @RequestMapping("/countAllTelRelations")
    public int countAllRelations(){
        return useTelService.countUseTelRelations();
    }

    @RequestMapping("/getAllUseTelRelations")
    public HashMap<String, Object> getAllUseTelRelations(){
        return useTelService.getAllUseTelRelations();
    }

    @RequestMapping("/findUserTelRelationByCustomerId")
    public HashMap<String, Object> findUserTelRelationByCustomerId(@RequestParam("customerId") String customerId){
        return useTelService.getUseTelRelationByCustomerId(customerId);
    }

    @RequestMapping("/findUserTelRelationByTelId")
    public HashMap<String, Object> findUserTelRelationByTelId(String telId){
        return useTelService.getUseTelRelationByTelId(telId);
    }


}

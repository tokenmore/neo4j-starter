package com.qt.neo4j.controller;

import com.qt.neo4j.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class TelephoneController {
    @Autowired
    private TelephoneService telephoneService;

    @RequestMapping("/countAllTels")
    public int countAllTels(){
        return telephoneService.countTelephone();
    }

    @RequestMapping("/getTelephoneByTelId")
    public HashMap<String, Object> getTelephoneByTelId(@RequestParam("telId")String telId){
        return telephoneService.getTelephoneByTelId(telId);
    }

    @RequestMapping("/getAllTelephone")
    public HashMap<String, Object> getAllTelephone(){
        return telephoneService.getAllTelephone();
    }
}

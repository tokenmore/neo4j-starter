package com.qt.neo4j.controller;

import com.qt.neo4j.dao.TelephoneRepsitory;
import com.qt.neo4j.entitiy.Telephone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TelephoneController {
    @Autowired
    private TelephoneRepsitory telephoneRepsitory;

    @RequestMapping("/countAllTels")
    public int countAllTels(){
        return telephoneRepsitory.countAll();
    }

    @RequestMapping("/getTelephoneByTelId")
    public Telephone getTelephoneByTelId(@RequestParam("telId")String telId){
        return telephoneRepsitory.getTelephoneByTelId(telId);
    }

    @RequestMapping("/getAllTelephone")
    public List<Telephone> getAllTelephone(){
        return telephoneRepsitory.getAllTelephone();

    }}

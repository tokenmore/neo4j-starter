package com.qt.neo4j.controller;

import com.qt.neo4j.dao.AccidentCaseRepository;
import com.qt.neo4j.service.AccidentCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AccidentCaseController {

    @Autowired
    private AccidentCaseRepository accidentCaseRepository;
    @Autowired
    private AccidentCaseService accidentCaseService;
    @RequestMapping("/countAccidentCase")
    public int countAccidentCase(){
        return accidentCaseRepository.countAccidentCase();
    }

    @RequestMapping("/findAccidentCaseById/{caseId}")
    public HashMap<String,Object> findAccidentCaseById(@PathVariable("caseId") String caseId){
        HashMap<String, Object> map = accidentCaseService.getAccidentCaseByCaseId(caseId);
        return map;
    }

    @RequestMapping("/getAllAccidentCase")
    public HashMap<String,Object> getAllAccidentCase(){
        HashMap<String, Object> map = accidentCaseService.getAllAccidentCase();
        return map;
    }
}

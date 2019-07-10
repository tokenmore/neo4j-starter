package com.qt.neo4j.controller;

import com.qt.neo4j.dao.AccidentCaseRepository;
import com.qt.neo4j.entitiy.AccidentCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccidentCaseController {

    @Autowired
    private AccidentCaseRepository accidentCaseRepository;

    @RequestMapping("/countAccidentCase")
    public int countAccidentCase(){
        return accidentCaseRepository.countAccidentCase();
    }

    @RequestMapping("/findAccidentCaseById")
    public AccidentCase findAccidentCaseById(@RequestParam("caseId") String caseId){
        return accidentCaseRepository.findAccidentCaseByCaseId(caseId);
    }

    @RequestMapping("/getAllAccidentCase")
    public List<AccidentCase> getAllAccidentCase(){
        return accidentCaseRepository.getAllAccidentCaseLabels();
    }
}

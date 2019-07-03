package com.qt.neo4j.service;

import com.qt.neo4j.dao.BussinessBelongRepsitory;
import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.BaseNode;
import com.qt.neo4j.entitiy.BussinessBelong;
import com.qt.neo4j.entitiy.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BussinessBelongService {
    @Autowired
    private BussinessBelongRepsitory bussinessBelongRepsitory;
    
    public Map<String,Object> findAllBussinessByEmpId(String empId){
        System.out.println(empId);
        List<BussinessBelong> list = bussinessBelongRepsitory.findAllBussinessBeLongsByEmpId(empId);
        System.out.println(list.toString());Map<String,Object> map = new HashMap<>();
//        List<BussinessBelong> allBussinessList = bussinessBelongRepsitory.findAllBussinessBeLongsByEmpId(empId);
//        System.out.println(allBussinessList.toString());
//        List<AccidentCase> accList = new ArrayList<>();
//        Map<String,Object> map = new HashMap<>();
//        for (BussinessBelong bussiness: allBussinessList) {
//            AccidentCase accidentCase = (AccidentCase) bussiness.getStartNode();
//            Employee employee = (Employee) bussiness.getEndNode();
//        }
//        map.put("acc",allBussinessList);
        map.put("emp",list);
        return map;
    }
}

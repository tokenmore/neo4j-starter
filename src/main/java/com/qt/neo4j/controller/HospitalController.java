package com.qt.neo4j.controller;

import com.qt.neo4j.dao.HospitalRepsitory;
import com.qt.neo4j.entitiy.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {
    @Autowired
    private HospitalRepsitory hospitalRepsitory;

    @RequestMapping("/countHospitalNums")
    public int countHospitalNums(){
        return hospitalRepsitory.countHospitalNums();
    }

    @RequestMapping("/getHospitalById")
    public Hospital getHospitalById(@RequestParam("hospitalId")String hospitalId){
        Hospital hospital = hospitalRepsitory.findByHospitalId(hospitalId);
        return hospital;
    }
}

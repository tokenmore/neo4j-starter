package com.qt.neo4j.controller;

import com.qt.neo4j.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping("/countHospitalNums")
    public int countHospitalNums(){
        return hospitalService.countHospital();
    }

    @RequestMapping("/getHospitalById")
    public HashMap<String,Object> getHospitalById(@RequestParam("hospitalId")String hospitalId){
      return hospitalService.getHospitalByHosId(hospitalId);
    }

    @RequestMapping("/getAllHospital")
    public HashMap<String,Object> getAllHospital(){
        return hospitalService.getAllHospital();
    }
}

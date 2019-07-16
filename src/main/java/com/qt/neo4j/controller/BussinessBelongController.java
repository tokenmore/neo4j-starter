package com.qt.neo4j.controller;

import com.qt.neo4j.service.BussinessBelongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BussinessBelongController {
    @Autowired
    private BussinessBelongService bussinessBelongService;

    @RequestMapping("/countAllBusssinessBelong")
   public  int countAllBusssinessBelong(){
       return bussinessBelongService.countAllBussinessBelong();
   }
   @RequestMapping("/getAllBussinessBelong")
   public HashMap<String,Object> getAllBussinessBelong(){
        return bussinessBelongService.findAllBussinessBelong();
   }

   @RequestMapping("/getBussinessBelongByCaseId")
    public HashMap<String,Object> getBussinessBelongByCaseId(@RequestParam("caseId") String caseId){
        return bussinessBelongService.findBussinessByCaseId(caseId);
   }

   @RequestMapping("/getBussinessngByCustomerId")
    public HashMap<String,Object> getBussinessngByCustomerId(@RequestParam("empId") String empId){
        return bussinessBelongService.findBussinessByEmpId(empId);
   }
}

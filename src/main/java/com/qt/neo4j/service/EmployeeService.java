package com.qt.neo4j.service;

import com.qt.neo4j.dao.EmployeeRepsitory;
import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.utils.Neo4jUtils;
import com.qt.neo4j.utils.RandomUtil;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private EmployeeRepsitory employeeRepsitory;

    public int countEmployee(){
        return employeeRepsitory.countEmployee();
    }

    public HashMap<String,Object> getAllEmployee(){
        String sql ="match (n:Employee) return id(n) as employeeId ,n.empId as empId limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node employee = new Node();

            employee.setId(record.get("employeeId").asLong());
            employee.setEmpid(record.get("empId").asString());
            employee.setLabelName(record.get("empId").asString());
            employee.setValue(RandomUtil.getRandomColor());
            employee.setType("Employee");
            nodeList.add(employee);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getEmployeeByEmpId(String empId){
        String sql ="match (n:Employee)where n.empId='"+empId+"' return id(n) as employeeId ,n.empId as empId limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node employee = new Node();

            employee.setId(record.get("employeeId").asLong());
            employee.setEmpid(record.get("empId").asString());
            employee.setLabelName(record.get("empId").asString());
            employee.setValue(RandomUtil.getRandomColor());
            employee.setType("Employee");
            nodeList.add(employee);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

package com.qt.neo4j.service;

import com.qt.neo4j.dao.HospitalRepsitory;
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
public class HospitalService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private HospitalRepsitory hospitalRepsitory;

    public int countHospital(){
        return hospitalRepsitory.countHospitalNums();
    }

    public HashMap<String,Object> getAllHospital(){
        String sql ="match (n:Hospital) return id(n)as hospitalId,n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node hospital = new Node();
            hospital.setId(record.get("hospitalId").asLong());
            hospital.setHospitaLevel(record.get("level").asString());
            hospital.setHospitalId(record.get("hosId").asString());
            hospital.setHospitalName(record.get("hosName").asString());
            hospital.setLabelName(record.get("hosName").asString());
            hospital.setColor(RandomUtil.getRandomColor());
            hospital.setType("Hospital");
            nodeList.add(hospital);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getHospitalByHosId(String hosId){
        String sql ="match (n:Hospital)where n.hospitalId='"+hosId+"' return id(n)as hospitalId,n.hospitaLevel" +
                " as level,n.hospitalId as hosId,n.hospitalName as hosName";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node hospital = new Node();
            hospital.setId(record.get("hospitalId").asLong());
            hospital.setHospitaLevel(record.get("level").asString());
            hospital.setHospitalId(record.get("hosId").asString());
            hospital.setHospitalName(record.get("hosName").asString());
            hospital.setLabelName(record.get("hosName").asString());
            hospital.setColor(RandomUtil.getRandomColor());
            hospital.setType("Hospital");
            nodeList.add(hospital);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

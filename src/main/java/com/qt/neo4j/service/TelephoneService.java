package com.qt.neo4j.service;

import com.qt.neo4j.dao.TelephoneRepsitory;
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
public class TelephoneService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private TelephoneRepsitory telephoneRepsitory;

    public int countTelephone(){
        return telephoneRepsitory.countAll();
    }

    public HashMap<String,Object> getAllTelephone(){
        HashMap<String, Object> map = new HashMap<>();
        String sql ="match (n:Telephone) return id(n) as telphoneId,n.telId as telId limit 10";
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node telephone = new Node();

            telephone.setId(record.get("telphoneId").asLong());
            telephone.setTelId(record.get("telId").asString());
            telephone.setLabelName(record.get("telId").asString());
            telephone.setColor(RandomUtil.getRandomColor());
            telephone.setType("Telephone");
            nodeList.add(telephone);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return map;
    }

    public HashMap<String,Object> getTelephoneByTelId(String telId){
        HashMap<String, Object> map = new HashMap<>();
        String sql ="match (n:Telephone)where n.telId ='"+telId+"' return id(n) as telphoneId,n.telId as telId ";
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node telephone = new Node();

            telephone.setId(record.get("telphoneId").asLong());
            telephone.setTelId(record.get("telId").asString());
            telephone.setLabelName(record.get("telId").asString());
            telephone.setColor(RandomUtil.getRandomColor());
            telephone.setType("Telephone");
            nodeList.add(telephone);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return map;
    }
}

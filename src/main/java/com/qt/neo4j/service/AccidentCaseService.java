package com.qt.neo4j.service;

import com.qt.neo4j.dao.AccidentCaseRepository;
import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.utils.Neo4jUtils;
import com.qt.neo4j.utils.RandomUtil;
import com.qt.neo4j.utils.SetNodeProperties;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AccidentCaseService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private AccidentCaseRepository accidentCaseRepository;

    public HashMap<String,Object> getAllAccidentCase(){
        String sql ="match (n:AccidentCase) return id(n) as accidentId,n.orgno as orgno,n.pfmoney as " +
                "pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node accident = new Node();

            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabelName(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            nodeList.add(accident);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getAccidentCaseByCaseId(String caseId){
        String sql ="match (n:AccidentCase) where n.caseId ='"+caseId+"' return  id(n) as accidentId,n.orgno as " +
                "orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node accident = new Node();

            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabelName(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            nodeList.add(accident);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getAllAccidentCaseLabels() {
        String sql ="match (n:AccidentCase)  return  id(n) as accidentId,n.orgno as " +
                "orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 20";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            Node accident = new Node();
            SetNodeProperties node = new SetNodeProperties();
            accident = node.setAccident(record);
            nodeList.add(accident);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public int countAccidentCase(){
        int i = accidentCaseRepository.countAccidentCase();
        return i;
    }

}

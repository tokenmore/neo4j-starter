package com.qt.neo4j.service;

import com.qt.neo4j.dao.RescueRespository;
import com.qt.neo4j.entitiy.Link;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.utils.Neo4jUtils;
import com.qt.neo4j.utils.SetLinksProperty;
import com.qt.neo4j.utils.SetNodeProperties;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RescureService {
    @Autowired
    private RescueRespository rescueRespository;
    @Autowired
    private Neo4jUtils neo4jUtils;

    public int countAllRescue(){
        return  rescueRespository.countAllRescue();
    }

    //得到所有的 治疗于 的关系
    public HashMap<String,Object> getAllRescueRelations(){
        String sql = "match (n:AccidentCase)-[r:治疗于]->(n1:Hospital) return id(n1)as hospitalId,n1.hospitaLevel as level,n1.hospitalId as hosId,n1.hospitalName as hosName,id(n) as accidentId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setHospital(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据caseId得到 治疗于 的关系
    public HashMap<String,Object> getRescueRelationByCaseId(String caseId){
        String sql = "match (n:AccidentCase)-[r:治疗于]->(n1:Hospital)where n.caseId='"+caseId+"'  return id(n1)as hospitalId,n1.hospitaLevel as level,n1.hospitalId as hosId,n1.hospitalName as hosName,id(n) as accidentId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setHospital(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据hosId得到 治疗于 的关系
    public HashMap<String,Object> getRescueRelationByCustomerId(String hosId){
        String sql = "match (n:AccidentCase)-[r:治疗于]->(n1:Hospital)where n1.hospitalId as hosId return id(n1)as hospitalId,n1.hospitaLevel as level,n1.hospitalId as hosId,n1.hospitalName as hosName,id(n) as accidentId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setHospital(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

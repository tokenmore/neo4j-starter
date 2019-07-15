package com.qt.neo4j.service;

import com.qt.neo4j.dao.BaoAnTelRelationRepsitory;
import com.qt.neo4j.entitiy.Link;
import com.qt.neo4j.entitiy.Links;
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
public class BaoAnTeleService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private BaoAnTelRelationRepsitory baoAnTelRelationRepsitory;

    //查询所有 是报案电话 关系的数量
    public int countAllBanAnRelation(){
        return baoAnTelRelationRepsitory.countAllBaoAnTelRelations();
    }

    //查询所有 是报案电话 的关系
    public HashMap<String,Object> getAllBaoAnTeleRelation(){
        String sql = "match (tel:Telephone)-[r:是报案电话]->(acc:AccidentCase) return id(tel) as telephoneId,id(acc) as accidentId,type(r) as relation ,acc.orgno as orgno,acc.pfmoney as pfmoney,acc.caseId as caseId,acc.qzflag as qzflag,tel.telId as telId limit 10;";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            Node telephone = nodeProperties.setTelephone(record);
            Node accident = nodeProperties.setAccident(record);
            Link link = linksProperty.setLinksProperty(record, "telephoneId", "accidentId", "是报案电话");
            nodeList.add(telephone);
            nodeList.add(accident);
            linkList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }


    //根据 telephoneId 获取 是报案电话关系
    public  HashMap<String,Object> getBaoAnTeleRelationByTelId(String telId){
        String sql = "match (tel:Telephone)-[r:是报案电话]->(acc:AccidentCase) where tel.telId='"+telId+"' return id(tel) as telephoneId,id(acc) as accidentId,type(r) as relation ,acc.orgno as orgno,acc.pfmoney as pfmoney,acc.caseId as caseId,acc.qzflag as qzflag,tel.telId as telId";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String, Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Link link = linksProperty.setLinksProperty(record, "telephoneId", "accidentId", "是报案电话");
            Node telephone = nodeProperties.setTelephone(record);
            Node accident = nodeProperties.setAccident(record);
            nodeList.add(telephone);
            nodeList.add(accident);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;

    }

    //根据 caseId 获取 是报案电话 关系
    public  HashMap<String,Object> getBaoAnTeleRelationByCaseId(String caseId){
        String sql = "match (tel:Telephone)-[r:是报案电话]->(acc:AccidentCase) where tel.telId='"+caseId+"' return id(tel) as telephoneId,id(acc) as accidentId,type(r) as relation ,acc.orgno as orgno,acc.pfmoney as pfmoney,acc.caseId as caseId,acc.qzflag as qzflag,tel.telId as telId";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String, Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Link link = linksProperty.setLinksProperty(record, "telephoneId", "accidentId", "是报案电话");
            Node telephone = nodeProperties.setTelephone(record);
            Node accident = nodeProperties.setAccident(record);
            nodeList.add(telephone);
            nodeList.add(accident);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;

    }
}

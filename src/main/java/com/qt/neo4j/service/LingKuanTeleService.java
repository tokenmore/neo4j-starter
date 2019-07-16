package com.qt.neo4j.service;

import com.qt.neo4j.dao.LingKuanTelRelationRepsitory;
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
public class LingKuanTeleService {
    @Autowired
    private LingKuanTelRelationRepsitory telRelationRepsitory;
    @Autowired
    private Neo4jUtils neo4jUtils;
    public int countAllLingKuanTeleRelations(){
        return telRelationRepsitory.countLingTelKuanRelations();
    }
    //得到所有 是领款电话 的关系
    public HashMap<String,Object> getAllLingKuanTeleRelations(){
        String sql ="match (t:Telephone)-[r:是领款电话]->(A:AccidentCase) return id(t) as telephoneId,id(A) as accidentId ,type(r) as relation,t.telId as telId,A.orgno as orgno,A.pfmoney as pfmoney,A.caseId as caseId,A.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setTelephone(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据手机号得到 是领款电话 的关系
    public HashMap<String,Object> getLingKuanTeleRelationByTelId(String telId){
        String sql ="match (t:Telephone)-[r:是领款电话]->(A:AccidentCase)where t.telephoneId='"+telId+"' return id(t) as telephoneId,id(A) as accidentId ,type(r) as relation,t.telId as telId,A.orgno as orgno,A.pfmoney as pfmoney,A.caseId as caseId,A.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setTelephone(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }


    //根据caseId得到 是领款电话 的关系
    public HashMap<String,Object> getLingKuanTeleRelationByCaseId(String caseId){
        String sql ="match (t:Telephone)-[r:是领款电话]->(A:AccidentCase)where A.caseId='"+caseId+"'return id(t) as telephoneId,id(A) as accidentId ,type(r) as relation,t.telId as telId,A.orgno as orgno,A.pfmoney as pfmoney,A.caseId as caseId,A.qzflag as qzflag limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setTelephone(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

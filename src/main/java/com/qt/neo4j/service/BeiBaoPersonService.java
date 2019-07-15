package com.qt.neo4j.service;

import com.qt.neo4j.dao.BeiBaoRelationRepsitory;
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
public class BeiBaoPersonService {
    @Autowired
    private BeiBaoRelationRepsitory beiBaoRelationRepsitory;
    @Autowired
    private Neo4jUtils neo4jUtils;

    public int countBeiBaoPerson(){
        return beiBaoRelationRepsitory.countAllBeiBaoRelations();
    }
    //获取所有 是被保人 的关系
    public HashMap<String,Object> getAllBeiBaoPerson(){
        String sql = "match (cu:Customer)-[r:是被保人]->(ac:AccidentCase) return id(cu) as customerId," +
                "id(ac) as accidentId,type(r) as relation,return ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId" +
                "as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            Link link = linksProperty.setLinksProperty(record, "customerId", "accidentId", "是被保人");
            nodeList.add(accident);
            nodeList.add(customer);
            linkList.add(link);
        }

        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;

    }

    //根据customerid 获取是被保人关系的
    public HashMap<String,Object> getBeiBaoPersonByCustomerId(String cutomerId){
        String sql = "match (cu:Customer)-[r:是被保人]->(ac:AccidentCase)where cu.customerId='"+cutomerId+"' return id(cu) as customerId ,id(ac) as accidentId,type(r) as relation ,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            Link link = linksProperty.setLinksProperty(record, "customerId", "accidentId", "是被保人");
            nodeList.add(accident);
            nodeList.add(customer);
            linkList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据caseId获取是被保人关系的
    public HashMap<String,Object> getBeiBaoPersonByCaseId(String caseId){
        String sql = "match (cu:Customer)-[r:是被保人]->(ac:AccidentCase)where cu.customerId='"+caseId+"' return id(cu) as customerId ,id(ac) as accidentId,type(r) as relation ,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            Link link = linksProperty.setLinksProperty(record, "customerId", "accidentId", "是被保人");
            nodeList.add(accident);
            nodeList.add(customer);
            linkList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

}

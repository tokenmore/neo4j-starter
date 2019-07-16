package com.qt.neo4j.service;

import com.qt.neo4j.dao.UseTelRepsitory;
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
public class UseTelService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private UseTelRepsitory useTelRepsitory;

    public int countUseTelRelations(){
        return useTelRepsitory.countAllTelRelations();
    }

    //获取所有 使用 的关系
    public HashMap<String,Object> getAllUseTelRelations(){
        String sql ="match (cu:Customer)-[r:使用]->(t:Telephone) return id(cu) as customerId,id(t) as telephoneId,type(r) as relation,t.telId as telId,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据TelId获取 使用 的关系
    public HashMap<String,Object> getUseTelRelationByTelId(String telId){
        String sql ="match (cu:Customer)-[r:使用]->(t:Telephone)where t.telId='"+telId+"' return id(cu) as customerId,id(t) as telephoneId,type(r) as relation,t.telId as telId,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据customerId获取 使用 的关系
    public HashMap<String,Object> getUseTelRelationByCustomerId(String customerid){
        String sql ="match (cu:Customer)-[r:使用]->(t:Telephone)where cu.customerId='"+customerid+"' return id(cu) as customerId,id(t) as telephoneId,type(r) as relation,t.telId as telId,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }


}

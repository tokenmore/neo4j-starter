package com.qt.neo4j.service;

import com.qt.neo4j.dao.CustomerRepsitory;
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
public class CustomerService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private CustomerRepsitory customerRepsitory;

    public int countCustomer(){
        return  customerRepsitory.countAll();
    }

    public HashMap<String,Object> getAllCustomer(){
        String sql ="match (cu:Customer)return id(cu) as customerId,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getCustomerByCustomerId(String customerId){
        String sql ="match (cu:Customer)where cu.customerId = '"+customerId+"'return id(cu) as customerId,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        while(result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

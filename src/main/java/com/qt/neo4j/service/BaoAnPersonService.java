package com.qt.neo4j.service;

import com.qt.neo4j.dao.BaoAnRelationRepsitory;
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
import java.util.LinkedList;
import java.util.List;

@Service
public class BaoAnPersonService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private BaoAnRelationRepsitory baoAnRelationRepsitory;

    /**
     * 获取报案人关系的数量
     * @return
     */
    public int countAllBaoAnPersonRelation(){
        return baoAnRelationRepsitory.countAllBaoAnRelations();
    }

    //获取所有 是报案人 关系
    public HashMap<String,Object>  getAllBaoAnPersonRelation(){
        String sql = "match (n:Customer)-[r:是报案人]->(n1:AccidentCase) return id(n) as sourceId,n.customerId as custId,n.customerName as custName, id(n1) as targetId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,type(r) as relation limit 10;";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linksList = new LinkedList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            Link link = linksProperty.setLinksProperty(record,"customerId","accidentId", "是报案人");
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            nodeList.add(customer);
            nodeList.add(accident);
            linksList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linksList);
        return map;
    }

    //根据 客户证件号 获取相关的 是报案人 关系
    public HashMap<String,Object> getBaoAnPersonRelationByCustomerId(String customerId){
        String sql = "match (n:Customer)-[r:是报案人]->(n1:AccidentCase)where n.customerId='"+customerId+"' return id(n) as sourceId,n.customerId as custId,n.customerName as custName, id(n1) as targetId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,type(r) as relation ";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linksList = new LinkedList<>();
        while(result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            nodeList.add(accident);
            nodeList.add(customer);
            Link link = linksProperty.setLinksProperty(record,"customerId","accidentId", "是报案人");
            linksList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linksList);
        return map;
    }


    //根据 案件和 获取相关的 是报案人的 关系
    public HashMap<String,Object> getBaoAnPersonRelationByCaseId(String caseId){
        String sql = "match (n:Customer)-[r:是报案人]->(n1:AccidentCase)where n1.caseId='"+caseId+"' return id(n) as customerId,n.customerId as custId,n.customerName as custName, id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,type(r) as relation ";
        StatementResult result = neo4jUtils.runSql(sql);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linksList = new LinkedList<>();
        while(result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
//            System.out.println(record.toString());
            Node customer = nodeProperties.setCustomer(record);
            Node accident = nodeProperties.setAccident(record);
            nodeList.add(accident);
            nodeList.add(customer);
            Link link = linksProperty.setLinksProperty(record,"customerId","accidentId", "是报案人");
            linksList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linksList);
        return map;
    }

}

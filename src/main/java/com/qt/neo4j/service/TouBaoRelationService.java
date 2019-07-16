package com.qt.neo4j.service;

import com.qt.neo4j.dao.TouBaoRelationRepsitory;
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
public class TouBaoRelationService {
    @Autowired
    private Neo4jUtils neo4jUtils;
    @Autowired
    private TouBaoRelationRepsitory touBaoRelationRepsitory;

    public  int countTouBaoRelations(){
        return touBaoRelationRepsitory.countAllTouBaoRelations();
    }

    //得到所有 是投保人 关系
    public HashMap<String,Object> getAllTouBaoRelations(){
        String sql ="match (cu:Customer)-[r:是投保人]->(ac:AccidentCase) return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据customerId得到 是投保人 关系
    public HashMap<String,Object> getATouBaoRelationByCustomerId(String customerId ){
        String sql ="match (cu:Customer)-[r:是投保人]->(ac:AccidentCase)where cu.cutomerId='"+customerId+"' return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据caseId得到 是投保人 关系
    public HashMap<String,Object> getATouBaoRelationByCaseId(String caseId){
        String sql ="match (cu:Customer)-[r:是投保人]->(ac:AccidentCase)where ac.caseId='"+caseId+"' return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setAccident(record));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

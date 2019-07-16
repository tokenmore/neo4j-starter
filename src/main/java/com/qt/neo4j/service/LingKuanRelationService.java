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
public class LingKuanRelationService {
    @Autowired
    private LingKuanTelRelationRepsitory lingKuanTelRelationRepsitory;
    @Autowired
    private Neo4jUtils neo4jUtils;

    public int countLingKuanRelations(){
        return lingKuanTelRelationRepsitory.countLingTelKuanRelations();
    }

    //得到所有的 是领款人的关系
    public HashMap<String,Object> getAllLingKuanRelations(){
        String sql = "match (cu:Customer)-[r:是领款人]->(ac:AccidentCase) return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
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
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }


    //根据caseID得到 是领款人的关系
    public HashMap<String,Object> getLingKuanRelationByCaseId(String caseId){
        String sql = "match (cu:Customer)-[r:是领款人]->(ac:AccidentCase)where ac.caseId = '"+caseId+"' return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
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
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    //根据customerId得到 是领款人的关系
    public HashMap<String,Object> getLingKuanRelationByCustomerId(String customerId){
        String sql = "match (cu:Customer)-[r:是领款人]->(ac:AccidentCase)where cu.customerId = '"+customerId+"' return id(cu) as customerId,id(ac) as accidentId,type(r) as relation,ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag ,cu.customerId as custId,cu.customerName as custName limit 10";
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
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

}

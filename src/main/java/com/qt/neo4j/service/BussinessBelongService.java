package com.qt.neo4j.service;

import com.qt.neo4j.dao.BussinessBelongRepsitory;
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
public class BussinessBelongService {
    @Autowired
    private BussinessBelongRepsitory bussinessBelongRepsitory;
    @Autowired
    private Neo4jUtils neo4jUtils;

    public int countAllBussinessBelong(){
        return bussinessBelongRepsitory.countAllBussinessBelong();
    }

    public HashMap<String,Object> findAllBussinessBelong(){
        String sql = "match (n:AccidentCase)-[r:业务归属于]-(n1:Employee) return id(n) as accidentId,id(n1) as employeeId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag,n1.empId as empId limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();

        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            Node accident = nodeProperties.setAccident(record);
            Node employee = nodeProperties.setEmployee(record);
            nodeList.add(accident);
            nodeList.add(employee);
            Link link = linksProperty.setLinksProperty(record, "accidentId", "employeeId", "业务归属于");
            linkList.add(link);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> findBussinessByEmpId(String empId){
        String sql = "match (n:AccidentCase)-[r:业务归属于]-(n1:Employee) where n1.empId='"+empId+"' return id(n) as accidentId,id(n1) as employeeId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag,n1.empId as empId limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setEmployee(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> findBussinessByCaseId(String caseId){
        String sql = "match (n:AccidentCase)-[r:业务归属于]-(n1:Employee) where n.caseId='"+caseId+"' return id(n) as accidentId,id(n1) as employeeId,type(r) as relation,n.orgno as orgno,n.pfmoney as pfmoney,n.caseId as caseId,n.qzflag as qzflag,n1.empId as empId limit 10;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        while (result.hasNext()){
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setEmployee(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

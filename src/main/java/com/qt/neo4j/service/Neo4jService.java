package com.qt.neo4j.service;

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
public class Neo4jService {

    @Autowired
    private Neo4jUtils neo4jUtils;

    public HashMap<String,Object> getAllFullRelations(){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7)," +
                "type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getFullRelationByCaseId(String caseId){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital)where n1.caseId='"+caseId+"' with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getFullRelationByCustomerId(String customerId){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) where n4.customerId='\"+customerId+\"' with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getFullRelationByTelId(String telId){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1) where n5.telId='"+telId+"' with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getFullRelationByHosId(String hosId){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital)where n2.hospitalId='"+hosId+"' with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return distinct id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

    public HashMap<String,Object> getFullRelationByEmpId(String empId){
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with " +
                "p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match " +
                "p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)" +
                "-[r4:是报案电话]->(n1)where n3.empId='"+empId+"' with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->" +
                "(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with " +
                "p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with " +
                "p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) " +
                "with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:" +
                "是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1." +
                "caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId" +
                " as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId," +
                "n4.customerId as custId,n4.customerName as customerName,id(n5) as telephoneId,n5.telId as " +
                "telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
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
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }
}

package com.qt.neo4j.service;

import com.qt.neo4j.entitiy.Link;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.utils.*;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class Neo4jService1 {

    @Autowired
    private Neo4jUtils neo4jUtils;

    public HashMap<String, Object> getAllFullRelations() {
        String sql = "match (cu:Customer)-[r]-(ac:AccidentCase) with cu,r,ac match " +
                "(ac)-[r1]-(n)  return distinct ac.orgno as orgno,ac.pfmoney as pfmoney,ac.accTime " +
                "as accTime,ac.caseId as caseId,ac.qzflag as qzflag,n.customerId as custId," +
                "n.customerName as custName,n.hospitaLevel as level,n.hospitalId as hosId," +
                "n.hospitalName as hosName,n.empId as empId,n.telId as telId,type(r1) as " +
                "relation,id(ac) as accidentId,id(n) as id1 limit 10";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            String relation3 = record.get("relation").asString();
            Node toubao = new Node();
            Node beibao = new Node();
            Node baoan = new Node();
            Node lingkuan = new Node();
            Node baoanTel = new Node();
            Node lingkuanTel = new Node();
            Node hos = new Node();
            Node employee = new Node();
            Node accident = new Node();
            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setAccTime(record.get("accTime").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabel(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            accident.setSize(35);
            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
            if (relation3.equals("是投保人")) {
                toubao.setId(record.get("id1").asLong());
                toubao.setCustomerId(record.get("custId").asString());
                toubao.setCustomerName(record.get("custName").asString());
                toubao.setLabel(record.get("custName").asString());
                toubao.setColor(RandomUtil.getRandomColor());
                toubao.setType("Customer");
                toubao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是投保人"));
            }
            if (relation3.equals("是被保人")) {
                beibao.setId(record.get("id1").asLong());
                beibao.setCustomerId(record.get("custId").asString());
                beibao.setCustomerName(record.get("custName").asString());
                beibao.setLabel(record.get("custName").asString());
                beibao.setColor(RandomUtil.getRandomColor());
                beibao.setType("Customer");
                beibao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是被保人"));
            }
            if (relation3.equals("是领款人")) {
                lingkuan.setId(record.get("id1").asLong());
                lingkuan.setCustomerId(record.get("custId").asString());
                lingkuan.setCustomerName(record.get("custName").asString());
                lingkuan.setLabel(record.get("custName").asString());
                lingkuan.setColor(RandomUtil.getRandomColor());
                lingkuan.setType("Customer");
                lingkuan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款人"));
            }
            if (relation3.equals("是报案人")) {
                baoan.setId(record.get("id1").asLong());
                baoan.setCustomerId(record.get("custId").asString());
                baoan.setCustomerName(record.get("custName").asString());
                baoan.setLabel(record.get("custName").asString());
                baoan.setColor(RandomUtil.getRandomColor());
                baoan.setType("Customer");
                baoan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案人"));
            }
            if (relation3.equals("治疗于")) {
                hos.setId(record.get("id1").asLong());
                hos.setHospitaLevel(record.get("level").asString());
                hos.setHospitalId(record.get("hosId").asString());
                hos.setHospitalName(record.get("hosName").asString());
                hos.setLabel(record.get("hosName").asString());
                hos.setColor(RandomUtil.getRandomColor());
                hos.setType("Hospital");
                hos.setSize(40);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "治疗于"));
            }
            if (relation3.equals("业务归属于")) {
                employee.setId(record.get("id1").asLong());
                employee.setEmpid(record.get("empId").asString());
                employee.setLabel(record.get("empId").asString());
                employee.setColor(RandomUtil.getRandomColor());
                employee.setType("Employee");
                employee.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, employee);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "业务归属于"));
            }
            if (relation3.equals("是报案电话")) {
                baoanTel.setId(record.get("id1").asLong());
                baoanTel.setTelId(record.get("telId").asString());
                baoanTel.setLabel(record.get("telId").asString());
                baoanTel.setColor(RandomUtil.getRandomColor());
                baoanTel.setType("Telephone");
                baoanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation3));
            }
            if (relation3.equals("是领款电话")) {
                lingkuanTel.setId(record.get("id1").asLong());
                lingkuanTel.setTelId(record.get("telId").asString());
                lingkuanTel.setLabel(record.get("telId").asString());
                lingkuanTel.setColor(RandomUtil.getRandomColor());
                lingkuanTel.setType("Telephone");
                lingkuanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation3));
            }
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }

    //根据案件号获取完整关系
    public HashMap<String, Object> getFullRelationByCaseId(String caseId) {
        String sql = "match (n:AccidentCase)-[r]-(n1) where n.caseId='" + caseId + "' return distinct n.orgno as orgno,n.pfmoney as pfmoney,n.accTime as accTime,n.caseId as caseId,n.qzflag as qzflag,n1.customerId as custId,n1.customerName as custName,n1.hospitaLevel as level,n1.hospitalId as hosId,n1.hospitalName as hosName,n1.empId as empId,n1.telId as telId,type(r) as relation,id(n) as accidentId,id(n1) as id1";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            String relation = record.get("relation").asString();
            Node toubao = new Node();
            Node beibao = new Node();
            Node baoan = new Node();
            Node lingkuan = new Node();
            Node baoanTel = new Node();
            Node lingkuanTel = new Node();
            Node hos = new Node();
            Node business = new Node();
            Node accident = new Node();
            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setAccTime(record.get("accTime").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabel(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            accident.setSize(35);
            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
            if (relation.equals("是投保人")) {
                toubao.setId(record.get("id1").asLong());
                toubao.setCustomerId(record.get("custId").asString());
                toubao.setCustomerName(record.get("custName").asString());
                toubao.setLabel(record.get("custName").asString());
                toubao.setColor(RandomUtil.getRandomColor());
                toubao.setType("Customer");
                toubao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是投保人"));
            }
            if (relation.equals("是被保人")) {
                beibao.setId(record.get("id1").asLong());
                beibao.setCustomerId(record.get("custId").asString());
                beibao.setCustomerName(record.get("custName").asString());
                beibao.setLabel(record.get("custName").asString());
                beibao.setColor(RandomUtil.getRandomColor());
                beibao.setType("Customer");
                beibao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是被保人"));
            }
            if (relation.equals("是领款人")) {
                lingkuan.setId(record.get("id1").asLong());
                lingkuan.setCustomerId(record.get("custId").asString());
                lingkuan.setCustomerName(record.get("custName").asString());
                lingkuan.setLabel(record.get("custName").asString());
                lingkuan.setColor(RandomUtil.getRandomColor());
                lingkuan.setType("Customer");
                lingkuan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款人"));
            }
            if (relation.equals("是报案人")) {
                baoan.setId(record.get("id1").asLong());
                baoan.setCustomerId(record.get("custId").asString());
                baoan.setCustomerName(record.get("custName").asString());
                baoan.setLabel(record.get("custName").asString());
                baoan.setColor(RandomUtil.getRandomColor());
                baoan.setType("Customer");
                baoan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案人"));
            }
            if (relation.equals("治疗于")) {
                hos.setId(record.get("id1").asLong());
                hos.setHospitaLevel(record.get("level").asString());
                hos.setHospitalId(record.get("hosId").asString());
                hos.setHospitalName(record.get("hosName").asString());
                hos.setLabel(record.get("hosName").asString());
                hos.setColor(RandomUtil.getRandomColor());
                hos.setType("Hospital");
                hos.setSize(40);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "治疗于"));
            }
            if (relation.equals("业务归属于")) {
                business.setId(record.get("id1").asLong());
                business.setEmpid(record.get("empId").asString());
                business.setLabel(record.get("empId").asString());
                business.setColor(RandomUtil.getRandomColor());
                business.setType("Employee");
                business.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, business);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "业务归属于"));
            }
            if (relation.equals("是报案电话")) {
                baoanTel.setId(record.get("id1").asLong());
                baoanTel.setTelId(record.get("telId").asString());
                baoanTel.setLabel(record.get("telId").asString());
                baoanTel.setColor(RandomUtil.getRandomColor());
                baoanTel.setType("Telephone");
                baoanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案电话"));
            }
            if (relation.equals("是领款电话")) {
                lingkuanTel.setId(record.get("id1").asLong());
                lingkuanTel.setTelId(record.get("telId").asString());
                lingkuanTel.setLabel(record.get("telId").asString());
                lingkuanTel.setColor(RandomUtil.getRandomColor());
                lingkuanTel.setType("Telephone");
                lingkuanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款电话"));
            }
        }
        String sql2 = "match (ac:AccidentCase)-[r]-(cu:Customer)where ac.caseId='" + caseId + "' with ac,cu,r match (cu)-[r1]-(tel:Telephone) return distinct  type(r1) as relation,id(cu) as source,id(tel) as target";
        StatementResult result1 = neo4jUtils.runSql(sql2);
        while (result1.hasNext()) {
            Record record = result1.next();
            Link link = new Link(record.get("source").asLong(), record.get("target").asLong(), record.get("relation").asString(), RandomUtil.getRandomColor());
            linkList.add(link);
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }

    //根据customerId获取完整关系
    public HashMap<String, Object> getFullRelationByCustomerId(String customerId) {
        String sql = "match (cu:Customer)-[r]-(ac:AccidentCase) where cu.customerId='" + customerId + "' with cu,r,ac match (ac)-[r1]-(n)  return distinct ac.orgno as orgno,ac.pfmoney as pfmoney,ac.accTime as accTime,ac.caseId as caseId,ac.qzflag as qzflag,n.customerId as custId,n.customerName as custName,n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName,n.empId as empId,n.telId as telId,type(r1) as relation,id(ac) as accidentId,id(n) as id1";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            String relation = record.get("relation").asString();
            Node toubao = new Node();
            Node beibao = new Node();
            Node baoan = new Node();
            Node lingkuan = new Node();
            Node baoanTel = new Node();
            Node lingkuanTel = new Node();
            Node hos = new Node();
            Node employee = new Node();
            Node accident = new Node();
            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setAccTime(record.get("accTime").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabel(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            accident.setSize(35);
            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
            if (relation.equals("是投保人")) {
                toubao.setId(record.get("id1").asLong());
                toubao.setCustomerId(record.get("custId").asString());
                toubao.setCustomerName(record.get("custName").asString());
                toubao.setLabel(record.get("custName").asString());
                toubao.setColor(RandomUtil.getRandomColor());
                toubao.setType("Customer");
                toubao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是投保人"));
            }
            if (relation.equals("是被保人")) {
                beibao.setId(record.get("id1").asLong());
                beibao.setCustomerId(record.get("custId").asString());
                beibao.setCustomerName(record.get("custName").asString());
                beibao.setLabel(record.get("custName").asString());
                beibao.setColor(RandomUtil.getRandomColor());
                beibao.setType("Customer");
                beibao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是被保人"));
            }
            if (relation.equals("是领款人")) {
                lingkuan.setId(record.get("id1").asLong());
                lingkuan.setCustomerId(record.get("custId").asString());
                lingkuan.setCustomerName(record.get("custName").asString());
                lingkuan.setLabel(record.get("custName").asString());
                lingkuan.setColor(RandomUtil.getRandomColor());
                lingkuan.setType("Customer");
                lingkuan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款人"));
            }
            if (relation.equals("是报案人")) {
                baoan.setId(record.get("id1").asLong());
                baoan.setCustomerId(record.get("custId").asString());
                baoan.setCustomerName(record.get("custName").asString());
                baoan.setLabel(record.get("custName").asString());
                baoan.setColor(RandomUtil.getRandomColor());
                baoan.setType("Customer");
                baoan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案人"));
            }
            if (relation.equals("治疗于")) {
                hos.setId(record.get("id1").asLong());
                hos.setHospitaLevel(record.get("level").asString());
                hos.setHospitalId(record.get("hosId").asString());
                hos.setHospitalName(record.get("hosName").asString());
                hos.setLabel(record.get("hosName").asString());
                hos.setColor(RandomUtil.getRandomColor());
                hos.setType("Hospital");
                hos.setSize(40);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "治疗于"));
            }
            if (relation.equals("业务归属于")) {
                employee.setId(record.get("id1").asLong());
                employee.setEmpid(record.get("empId").asString());
                employee.setLabel(record.get("empId").asString());
                employee.setColor(RandomUtil.getRandomColor());
                employee.setType("Employee");
                employee.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, employee);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "业务归属于"));
            }
            if (relation.equals("是报案电话")) {
                baoanTel.setId(record.get("id1").asLong());
                baoanTel.setTelId(record.get("telId").asString());
                baoanTel.setLabel(record.get("telId").asString());
                baoanTel.setColor(RandomUtil.getRandomColor());
                baoanTel.setType("Telephone");
                baoanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
            if (relation.equals("是领款电话")) {
                lingkuanTel.setId(record.get("id1").asLong());
                lingkuanTel.setTelId(record.get("telId").asString());
                lingkuanTel.setLabel(record.get("telId").asString());
                lingkuanTel.setColor(RandomUtil.getRandomColor());
                lingkuanTel.setType("Telephone");
                lingkuanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
        }
        String sql2 = "match (ac:AccidentCase)-[r]-(cu:Customer)where cu.customerId='" + customerId + "' with ac,cu,r match (cu)-[r1]-(tel:Telephone) return distinct  type(r1) as use,id(cu) as source,id(tel) as target";
        StatementResult result1 = neo4jUtils.runSql(sql2);
        while (result1.hasNext()) {
            Record record = result1.next();
            Link link = new Link(record.get("source").asLong(), record.get("target").asLong(), record.get("use").asString(), RandomUtil.getRandomColor());
            linkList.add(link);
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }

//    //根据电话号码查询完整关系
//    public HashMap<String, Object> getFullRelationByTelId(String telId) {
//        String sql = "match (tel:Telephone)-[r1]-(ac:AccidentCase) where tel.telId = '" + telId + "' with tel,r1,ac match (cu:Customer)-[r2]-(tel) with cu,tel,r1,r2,ac    match (ac)-[r3]-(n) return distinct n.telId as telId,id(ac) as accidentId, ac.orgno as orgno,ac.pfmoney as pfmoney,ac.accTime as accTime,ac.caseId as caseId,ac.qzflag as qzflag,n.customerId as custId,n.customerName as custName,id(cu) as customerId,type(r2) as relation2,n.empId as empId ,id(n) as id3,type(r3) as relation3,  n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName,id(n) as hospitalId;";
//        HashMap<String, Object> map = new HashMap<>();
//        StatementResult result = neo4jUtils.runSql(sql);
//        List<Node> nodeList = new ArrayList<>();
//        List<Link> linkList = new ArrayList<>();
//        while (result.hasNext()) {
//            Record record = result.next();
//            SetLinksProperty linksProperty = new SetLinksProperty();
//            String relation = record.get("relation3").asString();
//            Node toubao = new Node();
//            Node beibao = new Node();
//            Node baoan = new Node();
//            Node lingkuan = new Node();
//            Node baoanTel = new Node();
//            Node lingkuanTel = new Node();
//            Node hos = new Node();
//            Node employee = new Node();
//            Node accident = new Node();
//            accident.setId(record.get("accidentId").asLong());
//            accident.setOrgno(record.get("orgno").asString());
//            accident.setAccTime(record.get("accTime").asString());
//            accident.setPfmoney(record.get("pfmoney").asString());
//            accident.setCaseId(record.get("caseId").asString());
//            accident.setQzflag(record.get("qzflag").asString());
//            accident.setLabel(record.get("caseId").asString());
//            accident.setColor(RandomUtil.getRandomColor());
//            accident.setType("AccidentCase");
//            accident.setSize(35);
//            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
//            if (relation.equals("是投保人")) {
//                toubao.setId(record.get("id3").asLong());
//                toubao.setCustomerId(record.get("custId").asString());
//                toubao.setCustomerName(record.get("custName").asString());
//                toubao.setLabel(record.get("custName").asString());
//                toubao.setColor(RandomUtil.getRandomColor());
//                toubao.setType("Customer");
//                toubao.setSize(25);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", "是投保人"));
//            }
//            if (relation.equals("是被保人")) {
//                beibao.setId(record.get("id3").asLong());
//                beibao.setCustomerId(record.get("custId").asString());
//                beibao.setCustomerName(record.get("custName").asString());
//                beibao.setLabel(record.get("custName").asString());
//                beibao.setColor(RandomUtil.getRandomColor());
//                beibao.setType("Customer");
//                beibao.setSize(25);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", "是被保人"));
//            }
//            if (relation.equals("是领款人")) {
//                lingkuan.setId(record.get("id3").asLong());
//                lingkuan.setCustomerId(record.get("custId").asString());
//                lingkuan.setCustomerName(record.get("custName").asString());
//                lingkuan.setLabel(record.get("custName").asString());
//                lingkuan.setColor(RandomUtil.getRandomColor());
//                lingkuan.setType("Customer");
//                lingkuan.setSize(25);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", "是领款人"));
//            }
//            if (relation.equals("是报案人")) {
//                baoan.setId(record.get("id3").asLong());
//                baoan.setCustomerId(record.get("custId").asString());
//                baoan.setCustomerName(record.get("custName").asString());
//                baoan.setLabel(record.get("custName").asString());
//                baoan.setColor(RandomUtil.getRandomColor());
//                baoan.setType("Customer");
//                baoan.setSize(25);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", "是报案人"));
//            }
//            if (relation.equals("治疗于")) {
//                hos.setId(record.get("id3").asLong());
//                hos.setHospitaLevel(record.get("level").asString());
//                hos.setHospitalId(record.get("hosId").asString());
//                hos.setHospitalName(record.get("hosName").asString());
//                hos.setLabel(record.get("hosName").asString());
//                hos.setColor(RandomUtil.getRandomColor());
//                hos.setType("Hospital");
//                hos.setSize(40);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
//                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id3", "治疗于"));
//            }
//            if (relation.equals("业务归属于")) {
//                employee.setId(record.get("id3").asLong());
//                employee.setEmpid(record.get("empId").asString());
//                employee.setLabel(record.get("empId").asString());
//                employee.setColor(RandomUtil.getRandomColor());
//                employee.setType("Employee");
//                employee.setSize(25);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, employee);
//                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id3", "业务归属于"));
//            }
//            if (relation.equals("是报案电话")) {
//                baoanTel.setId(record.get("id3").asLong());
//                baoanTel.setTelId(record.get("telId").asString());
//                baoanTel.setLabel(record.get("telId").asString());
//                baoanTel.setColor(RandomUtil.getRandomColor());
//                baoanTel.setType("Telephone");
//                baoanTel.setSize(30);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", relation));
//            }
//            if (relation.equals("是领款电话")) {
//                lingkuanTel.setId(record.get("id3").asLong());
//                lingkuanTel.setTelId(record.get("telId").asString());
//                lingkuanTel.setLabel(record.get("telId").asString());
//                lingkuanTel.setColor(RandomUtil.getRandomColor());
//                lingkuanTel.setType("Telephone");
//                lingkuanTel.setSize(30);
//                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
//                linkList.add(linksProperty.setLinksProperty(record, "id3", "accidentId", relation));
//            }
//        }
//        String sql2 = "match (ac:AccidentCase)-[r]-(cu:Customer) with ac,cu,r match (cu)-[r1]-(tel:Telephone)where tel.telId='" + telId + "' return distinct  type(r1) as relation,id(cu) as source,id(tel) as target";
//        StatementResult result1 = neo4jUtils.runSql(sql2);
//        while (result1.hasNext()) {
//            Record record = result1.next();
//            Link link = new Link(record.get("source").asLong(), record.get("target").asLong(), record.get("relation").asString(), RandomUtil.getRandomColor());
//            linkList.add(link);
//        }
//        map.put("nodes", nodeList);
//        map.put("links", linkList);
//        return map;
//    }

    //根据  医院ID获取 多个 完整关系
    public HashMap<String, Object> getFullRelationByHosId(String hosId) {
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital)where n2.hospitalId='" + hosId + "' with " +
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
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setEmployee(record));
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setTelephone(record));
            linkList.add(linksProperty.setLinksProperty(record, "accidentId", "hospitalId", "治疗于"));
            linkList.add(linksProperty.setLinksProperty(record, "accidentId", "employeeId", "业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record, "customerId", "accidentId", "是投保人"));
            linkList.add(linksProperty.setLinksProperty(record, "customerId", "accidentId", "是被保人"));
            linkList.add(linksProperty.setLinksProperty(record, "customerId", "accidentId", "是报案人"));
            linkList.add(linksProperty.setLinksProperty(record, "customerId", "accidentId", "是领款人"));
            linkList.add(linksProperty.setLinksProperty(record, "customerId", "telephoneId", "使用"));
            linkList.add(linksProperty.setLinksProperty(record, "telephoneId", "accidentId", "是报案电话"));
            linkList.add(linksProperty.setLinksProperty(record, "telephoneId", "accidentId", "是领款电话"));
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }

    //根据 业务员号获取 多个 完整关系
    public HashMap<String, Object> getFullRelationByEmpId(String empId) {
        String sql = "match (n:Employee)-[r]-(ac:AccidentCase) where n.empId='" + empId + "' with n,ac,r match " +
                "(ac)-[r1]-(n1) return distinct id(n) as employeeId,n.empId as empId,id(ac) as accidentId," +
                "ac.orgno as orgno,ac.pfmoney as pfmoney,ac.caseId as caseId,ac.qzflag as qzflag,type(r1) " +
                "as relation,n1.customerId as custId,n1.customerName as custName,n1.hospitaLevel as level," +
                "n1.hospitalId as hosId,n1.hospitalName as hosName ,n1.telId as telId ,id(n1) as id1";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            String relation = record.get("relation").asString();
            Node toubao = new Node();
            Node beibao = new Node();
            Node baoan = new Node();
            Node lingkuan = new Node();
            Node baoanTel = new Node();
            Node lingkuanTel = new Node();
            Node hos = new Node();
            Node employee = new Node();
            Node accident = new Node();
            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setAccTime(record.get("accTime").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabel(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            accident.setSize(35);
            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
            if (relation.equals("是投保人")) {
                toubao.setId(record.get("id1").asLong());
                toubao.setCustomerId(record.get("custId").asString());
                toubao.setCustomerName(record.get("custName").asString());
                toubao.setLabel(record.get("custName").asString());
                toubao.setColor(RandomUtil.getRandomColor());
                toubao.setType("Customer");
                toubao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是投保人"));
            }
            if (relation.equals("是被保人")) {
                beibao.setId(record.get("id1").asLong());
                beibao.setCustomerId(record.get("custId").asString());
                beibao.setCustomerName(record.get("custName").asString());
                beibao.setLabel(record.get("custName").asString());
                beibao.setColor(RandomUtil.getRandomColor());
                beibao.setType("Customer");
                beibao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是被保人"));
            }
            if (relation.equals("是领款人")) {
                lingkuan.setId(record.get("id1").asLong());
                lingkuan.setCustomerId(record.get("custId").asString());
                lingkuan.setCustomerName(record.get("custName").asString());
                lingkuan.setLabel(record.get("custName").asString());
                lingkuan.setColor(RandomUtil.getRandomColor());
                lingkuan.setType("Customer");
                lingkuan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款人"));
            }
            if (relation.equals("是报案人")) {
                baoan.setId(record.get("id1").asLong());
                baoan.setCustomerId(record.get("custId").asString());
                baoan.setCustomerName(record.get("custName").asString());
                baoan.setLabel(record.get("custName").asString());
                baoan.setColor(RandomUtil.getRandomColor());
                baoan.setType("Customer");
                baoan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案人"));
            }
            if (relation.equals("治疗于")) {
                hos.setId(record.get("id1").asLong());
                hos.setHospitaLevel(record.get("level").asString());
                hos.setHospitalId(record.get("hosId").asString());
                hos.setHospitalName(record.get("hosName").asString());
                hos.setLabel(record.get("hosName").asString());
                hos.setColor(RandomUtil.getRandomColor());
                hos.setType("Hospital");
                hos.setSize(40);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "治疗于"));
            }
            if (relation.equals("业务归属于")) {
                employee.setId(record.get("id1").asLong());
                employee.setEmpid(record.get("empId").asString());
                employee.setLabel(record.get("empId").asString());
                employee.setColor(RandomUtil.getRandomColor());
                employee.setType("Employee");
                employee.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, employee);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "业务归属于"));
            }
            if (relation.equals("是报案电话")) {
                baoanTel.setId(record.get("id1").asLong());
                baoanTel.setTelId(record.get("telId").asString());
                baoanTel.setLabel(record.get("telId").asString());
                baoanTel.setColor(RandomUtil.getRandomColor());
                baoanTel.setType("Telephone");
                baoanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
            if (relation.equals("是领款电话")) {
                lingkuanTel.setId(record.get("id1").asLong());
                lingkuanTel.setTelId(record.get("telId").asString());
                lingkuanTel.setLabel(record.get("telId").asString());
                lingkuanTel.setColor(RandomUtil.getRandomColor());
                lingkuanTel.setType("Telephone");
                lingkuanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
        }
        String sql2 = "match (emp:Employee)-[r]-(ac:AccidentCase) where emp.empId='" + empId + "' with ac match (tel:Telephone)-[r1]-(ac) with tel,ac,type(r1) as r1 match (cu:Customer)-[r2]-(ac) return distinct id(cu) as source,id(tel) as target";
        StatementResult result1 = neo4jUtils.runSql(sql2);
        while (result1.hasNext()) {
            Record record = result1.next();
            Link link = new Link(record.get("source").asLong(), record.get("target").asLong(), "使用", RandomUtil.getRandomColor());
            linkList.add(link);
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }

    //根据客户姓名获取完整关系
    public HashMap<String, Object> getFullRelationByCustomerName(String customerName) {
        String sql = "match (cu:Customer)-[r]-(ac:AccidentCase) where cu.customerName =~ '.*" + customerName + ".*' with cu,r,ac match (ac)-[r1]-(n)  return distinct ac.orgno as orgno,ac.pfmoney as pfmoney,ac.accTime as accTime,ac.caseId as caseId,ac.qzflag as qzflag,n.customerId as custId,n.customerName as custName,n.hospitaLevel as level,n.hospitalId as hosId,n.hospitalName as hosName,n.empId as empId,n.telId as telId,type(r1) as relation,id(ac) as accidentId,id(n) as id1;";
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = neo4jUtils.runSql(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        while (result.hasNext()) {
            Record record = result.next();
            SetLinksProperty linksProperty = new SetLinksProperty();
            String relation = record.get("relation").asString();
            Node toubao = new Node();
            Node beibao = new Node();
            Node baoan = new Node();
            Node lingkuan = new Node();
            Node baoanTel = new Node();
            Node lingkuanTel = new Node();
            Node hos = new Node();
            Node employee = new Node();
            Node accident = new Node();
            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setAccTime(record.get("accTime").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabel(record.get("caseId").asString());
            accident.setColor(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");
            accident.setSize(35);
            nodeList = ListUtils.judgeNodeExistInList(nodeList, accident);
            if (relation.equals("是投保人")) {
                toubao.setId(record.get("id1").asLong());
                toubao.setCustomerId(record.get("custId").asString());
                toubao.setCustomerName(record.get("custName").asString());
                toubao.setLabel(record.get("custName").asString());
                toubao.setColor(RandomUtil.getRandomColor());
                toubao.setType("Customer");
                toubao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, toubao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是投保人"));
            }
            if (relation.equals("是被保人")) {
                beibao.setId(record.get("id1").asLong());
                beibao.setCustomerId(record.get("custId").asString());
                beibao.setCustomerName(record.get("custName").asString());
                beibao.setLabel(record.get("custName").asString());
                beibao.setColor(RandomUtil.getRandomColor());
                beibao.setType("Customer");
                beibao.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, beibao);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是被保人"));
            }
            if (relation.equals("是领款人")) {
                lingkuan.setId(record.get("id1").asLong());
                lingkuan.setCustomerId(record.get("custId").asString());
                lingkuan.setCustomerName(record.get("custName").asString());
                lingkuan.setLabel(record.get("custName").asString());
                lingkuan.setColor(RandomUtil.getRandomColor());
                lingkuan.setType("Customer");
                lingkuan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是领款人"));
            }
            if (relation.equals("是报案人")) {
                baoan.setId(record.get("id1").asLong());
                baoan.setCustomerId(record.get("custId").asString());
                baoan.setCustomerName(record.get("custName").asString());
                baoan.setLabel(record.get("custName").asString());
                baoan.setColor(RandomUtil.getRandomColor());
                baoan.setType("Customer");
                baoan.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoan);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", "是报案人"));
            }
            if (relation.equals("治疗于")) {
                hos.setId(record.get("id1").asLong());
                hos.setHospitaLevel(record.get("level").asString());
                hos.setHospitalId(record.get("hosId").asString());
                hos.setHospitalName(record.get("hosName").asString());
                hos.setLabel(record.get("hosName").asString());
                hos.setColor(RandomUtil.getRandomColor());
                hos.setType("Hospital");
                hos.setSize(40);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, hos);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "治疗于"));
            }
            if (relation.equals("业务归属于")) {
                employee.setId(record.get("id1").asLong());
                employee.setEmpid(record.get("empId").asString());
                employee.setLabel(record.get("empId").asString());
                employee.setColor(RandomUtil.getRandomColor());
                employee.setType("Employee");
                employee.setSize(25);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, employee);
                linkList.add(linksProperty.setLinksProperty(record, "accidentId", "id1", "业务归属于"));
            }
            if (relation.equals("是报案电话")) {
                baoanTel.setId(record.get("id1").asLong());
                baoanTel.setTelId(record.get("telId").asString());
                baoanTel.setLabel(record.get("telId").asString());
                baoanTel.setColor(RandomUtil.getRandomColor());
                baoanTel.setType("Telephone");
                baoanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, baoanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
            if (relation.equals("是领款电话")) {
                lingkuanTel.setId(record.get("id1").asLong());
                lingkuanTel.setTelId(record.get("telId").asString());
                lingkuanTel.setLabel(record.get("telId").asString());
                lingkuanTel.setColor(RandomUtil.getRandomColor());
                lingkuanTel.setType("Telephone");
                lingkuanTel.setSize(30);
                nodeList = ListUtils.judgeNodeExistInList(nodeList, lingkuanTel);
                linkList.add(linksProperty.setLinksProperty(record, "id1", "accidentId", relation));
            }
        }
        String sql2 = "match (ac:AccidentCase)-[r]-(cu:Customer)where cu.customerName =~ '.*\"+customerName+\".*' with ac,cu,r match (cu)-[r1]-(tel:Telephone) return distinct  type(r1) as use,id(cu) as source,id(tel) as target";
        StatementResult result1 = neo4jUtils.runSql(sql2);
        while (result1.hasNext()) {
            Record record = result1.next();
            Link link = new Link(record.get("source").asLong(), record.get("target").asLong(), record.get("use").asString(), RandomUtil.getRandomColor());
            linkList.add(link);
        }
        map.put("nodes", nodeList);
        map.put("links", linkList);
        return map;
    }
}

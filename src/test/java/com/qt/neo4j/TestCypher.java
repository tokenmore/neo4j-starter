package com.qt.neo4j;

import com.alibaba.fastjson.JSON;
import com.qt.neo4j.entitiy.Link;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.utils.Neo4jUtils;
import com.qt.neo4j.utils.RandomUtil;
import org.junit.Test;
import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestCypher {
    @Test
    public void searchCustomer(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "!QAZ2wsx"));
        Session session = driver.session();
        StatementResult result = session.run("match (n:Customer) return n.customerId as id,n.customerName as name limit 5");
//        System.out.println("size:"+result.list().size());
        while (result.hasNext()){
            Record record = result.next();
            System.out.println(record.toString());
            System.out.println(record.get("id").asString()+"name:"+record.get("name").asString());
        }
        session.close();
        driver.close();
    }

    @Test
    public void getAllNodes(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "!QAZ2wsx"));
        Session session = driver.session();
        String sql="match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with p1,n1,n2,r1 match p2=(n1)-[r2:业" +
                "务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match p3=(n4:Customer)-[r3:是投保人]->(n1) " +
                "with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)-[r4:是报案电话]->(n1) with n1,n2," +
                "n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->(n1) with p1,p2,p3,p4,p5,n1," +
                "n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with p1,p2,p3,p4,p5,p6,n1,n2,n3,n4," +
                "r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4," +
                "r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2," +
                "n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:是领款人]->(n1)   return id(n1) as accidentId," +
                "n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,id(n2) as " +
                "hospitalId,n2.hospitaLevel as level,n2.hospitalId as hosId,n2.hospitalName as hosName,id(n3)" +
                " as employeeId,n3.empId as empId,id(n4) as customerId,n4.customerId as custId,n4.customerName " +
                "as customerName,id(n5) as telphoneId,n5.telId as telId,type(r1),type(r2),type(r3),type(r4)," +
                "type(r5),type(r6),type(r7),type(r8),type(r9) limit 10";
        StatementResult result = session.run(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        int nodelen=nodeList.size();
        while (result.hasNext()){
            Record record = result.next();
            Node accident = new Node();
            Node customer = new Node();
            Node employee = new Node();
            Node telephone = new Node();
            Node hospital = new Node();
            Link toubao = new Link();
            Link beibao = new Link();
            Link baoan = new Link();
            Link lingkuan = new Link();
            Link lingkuanTel = new Link();
            Link baoanTel = new Link();
            Link useTel = new Link();
            Link rescueIn = new Link();
            Link bussiness = new Link();


            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabelName(record.get("caseId").asString());
            accident.setValue(RandomUtil.getRandomColor());

            hospital.setId(record.get("hospitalId").asLong());
            hospital.setHospitaLevel(record.get("level").asString());
            hospital.setHospitalId(record.get("hosId").asString());
            hospital.setHospitalName(record.get("hosName").asString());
            hospital.setLabelName(record.get("hosName").asString());
            hospital.setValue(RandomUtil.getRandomColor());

            employee.setId(record.get("employeeId").asLong());
            employee.setEmpid(record.get("empId").asString());
            employee.setLabelName(record.get("empId").asString());
            employee.setValue(RandomUtil.getRandomColor());

            customer.setId(record.get("customerId").asLong());
            customer.setCustomerId(record.get("custId").asString());
            customer.setCustomerName(record.get("customerName").asString());
            customer.setLabelName(record.get("customerName").asString());
            customer.setValue(RandomUtil.getRandomColor());

            telephone.setId(record.get("telphoneId").asLong());
            telephone.setTelId(record.get("telId").asString());
            telephone.setLabelName(record.get("telId").asString());
            telephone.setValue(RandomUtil.getRandomColor());

            rescueIn.setRelation("治疗于");
            toubao.setRelation("是投保人");
            baoan.setRelation("是报案人");
            lingkuan.setRelation("是领款人");
            beibao.setRelation("是被保人");
            bussiness.setRelation("业务归属于");
            lingkuanTel.setRelation("是领款电话");
            baoanTel.setRelation("是报案电话");
            useTel.setRelation("使用");
            toubao.setTarget(nodelen);beibao.setTarget(nodelen);baoan.setTarget(nodelen);lingkuan.setTarget(nodelen);
            rescueIn.setSource(nodelen);bussiness.setSource(nodelen);lingkuanTel.setTarget(nodelen);baoanTel.setTarget(nodelen);
            nodeList.add(accident);
            nodelen = nodelen+1;

            rescueIn.setTarget(nodelen);
             nodeList.add(hospital);
            nodelen = nodelen+1;

            toubao.setSource(nodelen);beibao.setSource(nodelen);
            baoan.setSource(nodelen);lingkuan.setSource(nodelen);useTel.setSource(nodelen);
            nodeList.add(customer);
            nodelen = nodelen+1;

            lingkuanTel.setSource(nodelen);baoanTel.setSource(nodelen);
            useTel.setTarget(nodelen);
            nodeList.add(telephone);
            nodelen = nodelen+1;

            bussiness.setTarget(nodelen);
            nodeList.add(employee);
            nodelen = nodelen+1;

            linkList.add(bussiness);
            linkList.add(toubao);linkList.add(beibao);linkList.add(baoan);linkList.add(lingkuan);
            linkList.add(useTel);linkList.add(lingkuanTel);linkList.add(baoanTel);linkList.add(rescueIn);
        }
        System.out.println("nodelen,size()"+nodelen);
        System.out.println(JSON.toJSON(linkList));
//        System.out.println(JSON.toJSON(nodeList));
        session.close();
        driver.close();
    }

    @Test
    public void getAllRelation(){
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "!QAZ2wsx"));
        Session session = driver.session();
        String sql="match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:是领款人]->(n1)   return p1,p2,p3,p4,p5,p6,p7,p8,p9 limit 10";
        StatementResult result = session.run(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        int i=0;
        while (result.hasNext()){
            Record record = result.next();
            System.out.println(record.get("p1"));
            System.out.println(record.get("p2"));
            System.out.println(record.get("p3"));
            System.out.println(record.get("p4"));
            System.out.println(record.get("p5"));
            System.out.println(record.get("p6"));
            System.out.println(record.get("p7"));
            System.out.println(record.get("p8"));
            System.out.println(record.get("p9"));
            i++;
        }
        System.out.println(i);
        session.close();
        driver.close();
    }

    @Test
    public void testSql(){
        String sql="match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with p1,n1,n2,r1 match p2=(n1)-[r2:业" +
                "务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match p3=(n4:Customer)-[r3:是投保人]->(n1) " +
                "with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)-[r4:是报案电话]->(n1) with n1,n2," +
                "n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->(n1) with p1,p2,p3,p4,p5,n1," +
                "n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with p1,p2,p3,p4,p5,p6,n1,n2,n3,n4," +
                "r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4," +
                "r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2," +
                "n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:是领款人]->(n1)   return id(n1) as accidentId," +
                "n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,id(n2) as " +
                "hospitalId,n2.hospitaLevel as level,n2.hospitalId as hosId,n2.hospitalName as hosName,id(n3)" +
                " as employeeId,n3.empId as empId,id(n4) as customerId,n4.customerId as custId,n4.customerName " +
                "as customerName,id(n5) as telphoneId,n5.telId as telId,type(r1),type(r2),type(r3),type(r4)," +
                "type(r5),type(r6),type(r7),type(r8),type(r9) limit 100";
        Neo4jUtils utils = new Neo4jUtils();
        HashMap<String, Object> map = utils.matchNodesAndRelations(sql);
        System.out.println(JSON.toJSON(map));
    }

}

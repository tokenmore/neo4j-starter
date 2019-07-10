package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;
import org.neo4j.driver.v1.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Neo4jUtils {
    private static String uri = "bolt://localhost:7687";
    private static  String username = "neo4j";
    private static String password = "!QAZ2wsx";
    private static Driver driver;
    private static Session session;

    public Neo4jUtils(){
        driver = GraphDatabase.driver(uri, AuthTokens.basic(username,password));
        session = driver.session();
    }


    public HashMap<String,Object> matchNodesAndRelations(String sql){
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = session.run(sql);
        List<Node> nodeList = new ArrayList<>();
        List<Links>  linkList = new ArrayList<>();
        int nodelen=nodeList.size();
        while (result.hasNext()){
            Record record = result.next();
            Node accident = new Node();
            Node customer = new Node();
            Node employee = new Node();
            Node telephone = new Node();
            Node hospital = new Node();
            Links toubao = new Links();
            Links beibao = new Links();
            Links baoan = new Links();
            Links lingkuan = new Links();
            Links lingkuanTel = new Links();
            Links baoanTel = new Links();
            Links useTel = new Links();
            Links rescueIn = new Links();
            Links bussiness = new Links();


            accident.setId(record.get("accidentId").asLong());
            accident.setOrgno(record.get("orgno").asString());
            accident.setPfmoney(record.get("pfmoney").asString());
            accident.setCaseId(record.get("caseId").asString());
            accident.setQzflag(record.get("qzflag").asString());
            accident.setLabelName(record.get("caseId").asString());
            accident.setValue(RandomUtil.getRandomColor());
            accident.setType("AccidentCase");

            hospital.setId(record.get("hospitalId").asLong());
            hospital.setHospitaLevel(record.get("level").asString());
            hospital.setHospitalId(record.get("hosId").asString());
            hospital.setHospitalName(record.get("hosName").asString());
            hospital.setLabelName(record.get("hosName").asString());
            hospital.setValue(RandomUtil.getRandomColor());
            hospital.setType("Hospital");

            employee.setId(record.get("employeeId").asLong());
            employee.setEmpid(record.get("empId").asString());
            employee.setLabelName(record.get("empId").asString());
            employee.setValue(RandomUtil.getRandomColor());
            employee.setType("Employee");

            customer.setId(record.get("customerId").asLong());
            customer.setCustomerId(record.get("custId").asString());
            customer.setCustomerName(record.get("customerName").asString());
            customer.setLabelName(record.get("customerName").asString());
            customer.setValue(RandomUtil.getRandomColor());
            customer.setType("Customer");

            telephone.setId(record.get("telphoneId").asLong());
            telephone.setTelId(record.get("telId").asString());
            telephone.setLabelName(record.get("telId").asString());
            telephone.setValue(RandomUtil.getRandomColor());
            telephone.setType("Telephone");
            //设置relation
            rescueIn.setRelation("治疗于");
            toubao.setRelation("是投保人");
            baoan.setRelation("是报案人");
            lingkuan.setRelation("是领款人");
            beibao.setRelation("是被保人");
            bussiness.setRelation("业务归属于");
            lingkuanTel.setRelation("是领款电话");
            baoanTel.setRelation("是报案电话");
            useTel.setRelation("使用");

            //设置Value
            toubao.setValue(RandomUtil.getRandomColor());
            beibao.setValue(RandomUtil.getRandomColor());
            baoan.setValue(RandomUtil.getRandomColor());
            lingkuan.setValue(RandomUtil.getRandomColor());
            lingkuanTel.setValue(RandomUtil.getRandomColor());
            baoanTel.setValue(RandomUtil.getRandomColor());
            useTel.setValue(RandomUtil.getRandomColor());
            rescueIn.setValue(RandomUtil.getRandomColor());
            bussiness.setValue(RandomUtil.getRandomColor());

            int flag = 0;
            System.out.println("nodelist的初始值是："+nodelen);
            flag = ListUtils.judgeAccidenCaseExistInList(nodeList, accident);
            if(flag == -1){ //flag=-1.是该元素不存在，所以nodelen+1
                System.out.println("Accident nodelen:"+nodelen);
                toubao.setTarget(nodelen);beibao.setTarget(nodelen);baoan.setTarget(nodelen);lingkuan.setTarget(nodelen);
                rescueIn.setSource(nodelen);bussiness.setSource(nodelen);lingkuanTel.setTarget(nodelen);baoanTel.setTarget(nodelen);
                nodeList.add(accident);
                nodelen = nodelen+1;
            }else{//flag ！= -1，元素存在，nodelen不用加1
                toubao.setTarget(flag);beibao.setTarget(flag);baoan.setTarget(flag);lingkuan.setTarget(flag);
                rescueIn.setSource(flag);bussiness.setSource(flag);lingkuanTel.setTarget(flag);baoanTel.setTarget(flag);
            }

            flag = ListUtils.judgeHospitalExistInList(nodeList,hospital);
            if(flag == -1){
                System.out.println("hospital nodelen:"+nodelen);
                rescueIn.setTarget(nodelen);
                nodeList.add(hospital);
                nodelen = nodelen+1;
            }else{
                rescueIn.setTarget(flag);
            }

            flag = ListUtils.judgeCustomerExistInList(nodeList,customer);
            if(flag == -1){
                System.out.println("customer nodelen:"+nodelen);
                toubao.setSource(nodelen);beibao.setSource(nodelen);
                baoan.setSource(nodelen);lingkuan.setSource(nodelen);useTel.setSource(nodelen);
                nodeList.add(customer);
                nodelen = nodelen+1;
            }else{
                toubao.setSource(flag);beibao.setSource(flag);
                baoan.setSource(flag);lingkuan.setSource(flag);useTel.setSource(flag);
            }

            flag = ListUtils.judgeTelephoneExistInList(nodeList,telephone);
            if(flag == -1){
                System.out.println("telephone nodelen:"+nodelen);

                lingkuanTel.setSource(nodelen);baoanTel.setSource(nodelen);
                useTel.setTarget(nodelen);
                nodeList.add(telephone);
                nodelen = nodelen+1;
            }else{
                lingkuanTel.setSource(flag);baoanTel.setSource(flag);
                useTel.setTarget(flag);
            }
            flag = ListUtils.judgeEmployeeExistInList(nodeList,employee);
            if(flag == -1){
                System.out.println("employee nodelen:"+nodelen);
                bussiness.setTarget(nodelen);
                nodeList.add(employee);
                nodelen = nodelen+1;
            }else{
                bussiness.setTarget(flag);
            }

            linkList.add(bussiness);
            linkList.add(toubao);linkList.add(beibao);linkList.add(baoan);linkList.add(lingkuan);
            linkList.add(useTel);linkList.add(lingkuanTel);linkList.add(baoanTel);linkList.add(rescueIn);
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return map;
    }

}

package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.Link;
import com.qt.neo4j.entitiy.Node;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
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

    public StatementResult runSql(String sql){
        StatementResult result = session.run(sql);
        return  result;

    }

    public HashMap<String,Object> matchNodesAndRelations(String sql){
        HashMap<String, Object> map = new HashMap<>();
        StatementResult result = session.run(sql);
        System.out.println(result.list().size());
        List<Node> nodeList = new ArrayList<>();
        List<Link>  linkList = new ArrayList<>();
        System.out.println();
        while (result.hasNext()){
            Record record = result.next();
            System.out.println(record.toString());
            SetNodeProperties nodeProperties = new SetNodeProperties();
            SetLinksProperty linksProperty = new SetLinksProperty();
            nodeList.add(nodeProperties.setAccident(record));
            nodeList.add(nodeProperties.setTelephone(record));
            nodeList.add(nodeProperties.setCustomer(record));
            nodeList.add(nodeProperties.setHospital(record));
            nodeList.add(nodeProperties.setEmployee(record));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","hospitalId","治疗于"));
            linkList.add(linksProperty.setLinksProperty(record,"accidentId","employeeId","业务归属于"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是投保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是被保人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是领款人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","accidentId","是报案人"));
            linkList.add(linksProperty.setLinksProperty(record,"customerId","telephoneId","使用"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是领款电话"));
            linkList.add(linksProperty.setLinksProperty(record,"telephoneId","accidentId","是报案电话"));
        }
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return  map;
    }

}

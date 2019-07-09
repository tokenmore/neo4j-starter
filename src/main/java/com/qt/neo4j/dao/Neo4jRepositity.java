package com.qt.neo4j.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface Neo4jRepositity  extends Neo4jRepository<Object,Long>{

    @Query(value = "MATCH (n) RETURN DISTINCT labels(n)")
    public List<Object> getALlNodes();

    @Query("match (n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with n1,n2,r1 match " +
            "(n1)-[r2:业务归属于]->(n3:Employee) with n1,n2,n3,r1,r2 " +
            "match (n4:Customer)-[r3:是投保人]->(n1) with n1,n2,n3,n4,r1,r2,r3 " +
            "match (n5:Telephone)-[r4:是报案电话]->(n1) with n1,n2,n3,n4,n5,r1,r2,r3,r4 " +
            "match (n4)-[r5:使用]->(n5) return n1,n2,n3,n4,n5,r1,r2,r3,r4,r5 limit 20")
    public List<HashMap<Object,Object>> getAllNodeAndRelation();


    @Query("match p1=(n1:Employee)<-[r1:业务归属于]-(n2:AccidentCase),p2=(n3:AccidentCase)-[r2:治疗于]->(n4:Hospital),p3=(n5:Customer)-[r3:是投保人]->(n6:AccidentCase),p4=(n7:Customer)-[r4:是被保人]->(n8:AccidentCase),p5=(n9:Customer)-[r5:是领款人]->(n10:AccidentCase),p6=(n11:Customer)-[r6:是报案人]->(n12:AccidentCase),p7=(n13:Customer)-[r7:使用]->(n14:Telephone),p8=(n15:Telephone)-[r8:是报案电话]->(n16:AccidentCase),p9=(n17:Telephone)-[r:是领款电话]->(n18:AccidentCase) return p1,p2,p3,p4,p5,p6,p7,p8,p9 limit 5")
    public List<HashMap<String,Object>> getAllRelations();
}

package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BaseRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

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
    public List<BaseRelation> getAllNodeAndRelation();
}

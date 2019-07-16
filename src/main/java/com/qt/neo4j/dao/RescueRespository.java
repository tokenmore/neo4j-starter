package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.RescueInRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 案件和医院实体的关系
 */
@Repository
public interface RescueRespository extends Neo4jRepository<RescueInRelation,Long> {

    @Query("match p=(n:AccidentCase)-[r:治疗于]->(n1:Hospital) return count(p) ")
    public int countAllRescue();

}

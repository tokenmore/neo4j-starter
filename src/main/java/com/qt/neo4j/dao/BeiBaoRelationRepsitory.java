package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BeiBaoRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 案件和客户的被保关系
 */

@Repository
public interface BeiBaoRelationRepsitory extends Neo4jRepository<BeiBaoRelation,Long> {

    @Query("match p=(n:Customer)-[r:是被保人]->(n1:AccidentCase) return count(p)")
    public int countAllBeiBaoRelations();
}


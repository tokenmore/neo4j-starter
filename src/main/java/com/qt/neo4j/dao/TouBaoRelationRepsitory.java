package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.TouBaoRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouBaoRelationRepsitory extends Neo4jRepository<TouBaoRelation,Long> {

    @Query("match p=(n:Customer)-[r:是投保人]->(n1:AccidentCase) return count(p)")
    public int countAllTouBaoRelations();
}

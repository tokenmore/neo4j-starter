package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.UserTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseTelRepsitory extends Neo4jRepository<UserTelRelation,Long> {
    @Query("match p=(n:Customer)-[r:使用]->(n1:Telephone) return count(p)")
    public int countAllTelRelations();
}

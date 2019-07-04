package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.UserTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UseTelRepsitory extends Neo4jRepository<UserTelRelation,Long> {
    @Query("match p=(n:Customer)-[r:使用]->(n1:Telephone) return count(p)")
    public int countAllTelRelations();

    @Query("match p=(n1:Customer)-[r:使用]->(n2:Telephone) return p ")
    public List<UserTelRelation> findAllTelRelation();

    @Query("match p=(n1:Customer)-[r:使用]->(n2:Telephone)where n1.customerId={customerId} return p ")
    public List<UserTelRelation> findUserTelRelationByCustomerId(@Param("customerId")String customerId);

    @Query("match p=(n1:Customer)-[r:使用]->(n2:Telephone)where n2.telId={telId} return p ")
    public List<UserTelRelation> findUserTelRelationByTelId(@Param("telId")String telId);




}

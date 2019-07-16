package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.LingKuanTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 案件和电话的关系
 */
@Repository
public interface LingKuanTelRelationRepsitory extends Neo4jRepository<LingKuanTelRelation,Long> {
    @Query("match P=(n:Telephone)-[r:是领款电话]->(n1:AccidentCase)  return count(P) ")
    public int countLingTelKuanRelations();
}

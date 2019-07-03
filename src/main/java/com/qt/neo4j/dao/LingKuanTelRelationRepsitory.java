package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.LingKuanTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件和电话的关系
 */
@Repository
public interface LingKuanTelRelationRepsitory extends Neo4jRepository<LingKuanTelRelation,Long> {
    @Query("match (n:Telephone)-[r:是领款电话]->(n1:AccidentCase)  return n,n1,r ")
    public List<LingKuanTelRelation> getAllLingTelKuanRelations();
    //根据案件ID查询领款电话关系
    @Query("match (n:Telephone)-[r:是领款电话]->(n1:AccidentCase)where n1.caseId={caseId}  return n,n1,r ")
    public List<LingKuanTelRelation> getLingKuanTelRelationByCaseId(@Param("caseId") String caseId);
    //根据电话号码查询查询领款电话关系
    @Query("match (n:Telephone)-[r:是领款电话]->(n1:AccidentCase)where n.telId={telId}  return n,n1,r ")
    public List<LingKuanTelRelation> getLingKuanTelRelationBytelId(@Param("telId") String telId);
}

package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BeiBaoRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件和客户的被保关系
 */

@Repository
public interface BeiBaoRelationRepsitory extends Neo4jRepository<BeiBaoRelation,Long> {

    @Query("match p=(n:Customer)-[r:是被保人]->(n1:AccidentCase) return count(p)")
    public int countAllBeiBaoRelations();

    @Query("match p=(n1:Customer)-[r:是被保人]->(n2:AccidentCase) return p ")
    public List<BeiBaoRelation> findAllBeiBaoRelation();

    //根据客户ID查询客户和案件的被保关系
    @Query("match p=(n1:Customer)-[r:是被保人]->(n2:AccidentCase)where n1.customerId={customerId} return p ")
    public List<BeiBaoRelation> findBeiBaoRelationByCustomerId(@Param("customerId")String customerId);
    //根据案件id查询案件和客户关系
    @Query("match p=(n1:Customer)-[r:是被保人]->(n2:AccidentCase)where n2.caseId={caseId} return p ")
    public List<BeiBaoRelation> findBeiBaoRelationBycaseId(@Param("caseId")String caseId);

    //根据案件的caseId获取到对应的customeId
    @Query("match  (n:Customer)-[r:是领款人]->(n1:AccidentCase) where n1.caseId={caseId} return n.customerId ")
    public  String getCustomerIdByCaseId(@Param("caseId") String caseId);
}


package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.LingKuanRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户是本案件的领款人
 */
@Repository
public interface LingKuanRelationRepsitory extends Neo4jRepository<LingKuanRelation,Long> {
    //查询领款人的关系有多少条
    @Query("match p=(n:Customer)-[r:是领款人]->(n1:AccidentCase) return count(p)")
    public int countAllLingKuanRelations();
    //查询所有客户和案件的领款关系
    @Query("match p=(n1:Customer)-[r:是领款人]->(n2:AccidentCase) return p ")
    public List<LingKuanRelation> findAllLingKuanRelation();
    //根据客户cusTomerId查询领款关系
    @Query("match p=(n1:Customer)-[r:是领款人]->(n2:AccidentCase)where n1.customerId={customerId} return p ")
    public List<LingKuanRelation> findLingKuanRelationByCustomerId(@Param("customerId")String customerId);
    //根据案件caseId查询 客户和案件的领款关系
    @Query("match p=(n1:Customer)-[r:是领款人]->(n2:AccidentCase)where n1.caseId={caseId} return p ")
    public List<LingKuanRelation> findLingKuanRelationBycaseId(@Param("caseId")String caseId);
    //根据案件的caseId获取到对应的customeId
    @Query("match  (n:Customer)-[r:是领款人]->(n1:AccidentCase) where n1.caseId={caseId} return n.customerId ")
    public  String getCustomerIdByCaseId(@Param("caseId") String caseId);
}

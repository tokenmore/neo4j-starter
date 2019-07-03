package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.BaoAnRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户是该案件的报案人
 */
@Repository
public interface BaoAnRelationRepsitory extends Neo4jRepository<BaoAnRelation,Long> {
    //查询所有客户和案件的报案关系数量
    @Query("match p=(n:Customer)-[r:是报案人]->(n1:AccidentCase) return count(p)")
    public int countAllBaoAnRelations();
    //查询所有客户和案件的报案关系
    @Query("match p=(n1:Customer)-[r:是报案人]->(n2:AccidentCase) return p  ")
    public List<BaoAnRelation> findAllBaoAnRelation();
    //根据客户customerId查询所有客户和案件的报案关系
    @Query("match p=(n1:Customer)-[r:是报案人]->(n2:AccidentCase)where n1.customerId={customerId} return p ")
    public List<BaoAnRelation> findBaoAnRelationByCustomerId(@Param("customerId")String customerId);

    //通过caseId查询客户和案件的关系
    @Query("match p=(n1:Customer)-[r:是报案人]->(n2:AccidentCase)where n2.caseId={caseId} return p ")
    public List<BaoAnRelation> findBaoAnRelationBycaseId(@Param("caseId")String caseId);

    //通过办案人关系，通过案件的caseID获取到报案客户的customerId
    @Query("match (n1:Customer)-[r:是报案人]->(n2:AccidentCase) where n2.caseId={caseId} return n1.customerId")
    public String getCustomerIdByCaseId(@Param("caseId") String caseId);
}

package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.TouBaoRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouBaoRelationRepsitory extends Neo4jRepository<TouBaoRelation,Long> {

    @Query("match p=(n:Customer)-[r:是投保人]->(n1:AccidentCase) return count(p)")
    public int countAllTouBaoRelations();
    @Query("match (n1:Customer)-[r:是投保人]->(n2:AccidentCase) return n1,n2,r ")
    public List<TouBaoRelation> findAllTouBaoRelation();

    @Query("match p=(n1:Customer)-[r:是投保人]->(n2:AccidentCase)where n1.customerId={customerId} return p ")
    public List<TouBaoRelation> findTouBaoRelationByCustomerId(@Param("customerId")String customerId);

    @Query("match p=(n1:Customer)-[r:是投保人]->(n2:AccidentCase)where n2.caseId={caseId} return p ")
    public List<TouBaoRelation> findTouBaoRelationBycaseId(@Param("caseId")String caseId);
    //根据案件的caseId获取到对应的customeId
    @Query("match  (n:Customer)-[r:是领款人]->(n1:AccidentCase) where n1.caseId={caseId} return n.customerId ")
    public  String getCustomerIdByCaseId(@Param("caseId") String caseId);
}

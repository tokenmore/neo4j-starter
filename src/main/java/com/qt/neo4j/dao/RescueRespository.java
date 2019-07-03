package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.RescueInRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件和医院实体的关系
 */
@Repository
public interface RescueRespository extends Neo4jRepository<RescueInRelation,Long> {

    @Query("match (n:AccidentCase)-[r:治疗于]->(n1:Hospital) return n1,n,r ")
    public List<RescueInRelation> findAllRescue();

    /**
     * 根据案件Id查询治疗关系
     * @param caseId
     * @return
     */
    @Query("match (n:AccidentCase)-[r:治疗于]->(n1:Hospital) where n.caseId={caseId} return n1,n,r")
    public List<RescueInRelation> findRescueInRelationByCaseId(@Param("caseId")String caseId);

    /**
     * 根据医院Id查询案件与医院的关系
     * @param hosId
     * @return
     */
    @Query("match (n:AccidentCase)-[r:治疗于]->(n1:Hospital) where n1.hospitalId={hosId} return n1,n,r")
    public List<RescueInRelation> findRescueInRelationByhosId(@Param("hosId")String hosId);
}

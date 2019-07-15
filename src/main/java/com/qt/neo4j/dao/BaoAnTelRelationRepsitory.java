package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BaoAnTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电话是案件的报案电话
 */
@Repository
public interface BaoAnTelRelationRepsitory extends Neo4jRepository<BaoAnTelRelation,Long> {
    //查询案件和电话是办案电话的数量
    @Query("match p=(n:Telephone)-[r:是报案电话]->(n1:AccidentCase) return count(p)")
    public int countAllBaoAnTelRelations();
//    @Query("match p=(n:Telephone)-[r:是报案电话]->(n1:AccidentCase) return n1,n,r limit 100 ")
//    public List<BaoAnTelRelation> getAllTelRelation();
    //根据案件Id查询报案电话关系
    @Query("match (n:Telephone)-[r:是报案电话]->(n1:AccidentCase) where n1.caseId={accid} return n1,n,r")
    public List<BaoAnTelRelation> getBaoAnTelRelationBycaseId(@Param("accid") String accId);
    //根据电话号码查询报案电话关系
    @Query("match (n:Telephone)-[r:是报案电话]->(n1:AccidentCase) where n.telId={TelId} return n1,n,r")
    public List<BaoAnTelRelation> getBaoAnTelRelationByTelId(@Param("TelId") String TelId);
}

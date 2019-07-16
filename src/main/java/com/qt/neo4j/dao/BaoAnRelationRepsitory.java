package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BaoAnRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 客户是该案件的报案人
 */
@Repository
public interface BaoAnRelationRepsitory extends Neo4jRepository<BaoAnRelation,Long> {
    //查询所有客户和案件的报案关系数量
    @Query("match p=(n:Customer)-[r:是报案人]->(n1:AccidentCase) return count(p)")
    public int countAllBaoAnRelations();
}

package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BaoAnTelRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 电话是案件的报案电话
 */
@Repository
public interface BaoAnTelRelationRepsitory extends Neo4jRepository<BaoAnTelRelation,Long> {
    //查询案件和电话是办案电话的数量
    @Query("match p=(n:Telephone)-[r:是报案电话]->(n1:AccidentCase) return count(p)")
    public int countAllBaoAnTelRelations();
}

package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.LingKuanRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 客户是本案件的领款人
 */
@Repository
public interface LingKuanRelationRepsitory extends Neo4jRepository<LingKuanRelation,Long> {
    //查询领款人的关系有多少条
    @Query("match p=(n:Customer)-[r:是领款人]->(n1:AccidentCase) return count(p)")
    public int countAllLingKuanRelations();
}

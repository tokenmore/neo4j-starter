package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BussinessBelong;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 业务员和案件的关系
 */
@Repository
public interface BussinessBelongRepsitory extends Neo4jRepository<BussinessBelong,Long> {

    @Query("match p=(n:AccidentCase)-[r:业务归属于]-(n1:Employee) return count(p)")
    public int countAllBussinessBelong();



}

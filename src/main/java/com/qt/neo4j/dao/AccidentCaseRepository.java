package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.AccidentCase;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 案件接口
 */
@Repository
public interface AccidentCaseRepository extends Neo4jRepository<AccidentCase,Long> {
    @Query("match (n:AccidentCase) return count(n)")
    public  int countAccidentCase();
}

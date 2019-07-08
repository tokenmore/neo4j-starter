package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.AccidentCase;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 案件接口
 */
@Repository
public interface AccidentCaseRepository extends Neo4jRepository<AccidentCase,Long> {
    //查询所有案件实体
    @Query("match (n:AccidentCase) return n")
    public List<AccidentCase> getAllAccidentCaseLabels();
    //根据caseId查询案件实体
    @Query("match (n:AccidentCase) where n.caseId ={caseId} return n")
    public AccidentCase findAccidentCaseByCaseId(@Param("caseId") String caseId);
    //查询案件实体的数量
    @Query("match (n:AccidentCase) return count(n)")
    public  int countAccidentCase();

    //获取所有案件的caseId
    @Query("match (n:AccidentCase) return n.caseId limit 1")
    public List<String> getAllCaseId();
}

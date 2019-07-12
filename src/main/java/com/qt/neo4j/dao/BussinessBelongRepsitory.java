package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.relation.BussinessBelong;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务员和案件的关系
 */
@Repository
public interface BussinessBelongRepsitory extends Neo4jRepository<BussinessBelong,Long> {
    @Query("match (n1:AccidentCase),(n2:Employee) where n1.caseId={caseId} and n2.empId={empId} p=create " +
            "(n2)-[r:业务归属于]->(n2) return p")
    public List<BussinessBelong> createBussinessBeLongWithStartNodeANdEndNode(@Param("caseId") String caseId,@Param("empId") String empId);

    //查询所有案件和员工的信息
    @Query("match p=(n:AccidentCase)-[r:业务归属于]-(n1:Employee) return p limit 10" )
    public List<BussinessBelong> findAllBussinessBeLongs();

    //根据员工号查询所有案件和员工的信息
    @Query("match p=(n:AccidentCase)-[r:业务归属于]-(n1:Employee) where n1.empId={empId} return p" )
    public List<BussinessBelong> findAllBussinessBeLongsByEmpId(@Param("empId") String empId);

    //根据案件id查询业务员和案件的关系
    @Query("match p=(n:AccidentCase)-[r:业务归属于]-(n1:Employee) where n.caseId={caseId} return p" )
    public List<BussinessBelong> findAllBussinessBeLongsBycaseId(@Param("caseId") String caseId);

}

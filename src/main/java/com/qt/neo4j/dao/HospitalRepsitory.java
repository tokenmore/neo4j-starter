package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Hospital;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepsitory extends Neo4jRepository<Hospital,Long> {

    @Query("match (n:Hospital) return count(n)")
    public  int countHospitalNums();

    @Query("match (n:Hospital) where  n.hospitalId={hospitalId} return n")
    public Hospital findByHospitalId(@Param("hospitalId") String hospitalId);
}

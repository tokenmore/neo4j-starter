package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Telephone;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepsitory extends Neo4jRepository<Telephone,Long> {
    @Query("match (n:Telephone) return count(n)")
    public int countAll();
}

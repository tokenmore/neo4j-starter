package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Customer;
import com.qt.neo4j.entitiy.Telephone;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepsitory extends Neo4jRepository<Telephone,Long> {
    @Query("match (n:Telephone) return count(n)")
    public int countAll();

    @Query("match (n:Telephone) where n.telId={telId} return n ")
    public Telephone getTelephoneByTelId(@Param("telId") String telId);

    @Query("match (n:Telephone) return n limit 15")
    public List<Telephone> getAllTelephone();
}

package com.qt.neo4j.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Neo4jRepositity  extends Neo4jRepository<Object,Long>{

    @Query(value = "MATCH (n) RETURN DISTINCT labels(n)")
    public List<Object> getALlNodes();
}

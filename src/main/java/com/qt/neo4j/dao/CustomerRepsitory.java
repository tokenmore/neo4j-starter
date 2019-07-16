package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Customer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * 客户实体
 */
@Repository
public interface CustomerRepsitory extends Neo4jRepository<Customer,Long> {
    @Query("match (n:Customer) return count(n)")
    public int countAll();
}

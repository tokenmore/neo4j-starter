package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Customer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户实体
 */
@Repository
public interface CustomerRepsitory extends Neo4jRepository<Customer,Long> {
    @Query("match (n:Customer) return count(n)")
    public int countAll();
    //根据客户ID查询客户实体
    @Query("match (n:Customer) where n.customerId={customerId} return n ")
    public Customer getCustomerByCustomerId(@Param("customerId") String customerId);

    @Query("match (n:Customer) return n  ")
    public List<Customer> getAllCustomer();
}

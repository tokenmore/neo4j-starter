package com.qt.neo4j.dao;

import com.qt.neo4j.entitiy.Employee;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepsitory extends Neo4jRepository<Employee,Long> {
    @Query("match (n:Employee) return count(n)")
    public int countEmployee();
}

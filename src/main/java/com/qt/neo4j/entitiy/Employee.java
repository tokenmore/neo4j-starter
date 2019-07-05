package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * 业务员实体
 */

@NodeEntity
public class Employee extends BaseNode{

    private String empId;

    @Relationship(type = "业务归属于",direction = Relationship.INCOMING)
    private List<AccidentCase> accidentCases;

    public Employee() {

    }

    public Employee(String empId) {
        this.empId = empId;
    }

    public List<AccidentCase> getAccidentCases() {
        return accidentCases;
    }

    public void setAccidentCases(List<AccidentCase> accidentCases) {
        this.accidentCases = accidentCases;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", accidentCases=" + accidentCases +
                '}';
    }
}

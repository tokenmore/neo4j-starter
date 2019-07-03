package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.*;

/**
 * 业务归属于关系
 */

@RelationshipEntity(type = "BussinessBelong")
public class BussinessBelong {

    @Id
    @GeneratedValue
    private Long id;

    @EndNode
    private  Employee employee;

    @StartNode
    private  AccidentCase accidentCase;


    public BussinessBelong() { }

    public BussinessBelong(Employee employee, AccidentCase accidentCase) {
        this.employee = employee;
        this.accidentCase = accidentCase;
    }

    public AccidentCase getAccidentCase() {
        return accidentCase;
    }

    public void setAccidentCase(AccidentCase accidentCase) {
        this.accidentCase = accidentCase;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}

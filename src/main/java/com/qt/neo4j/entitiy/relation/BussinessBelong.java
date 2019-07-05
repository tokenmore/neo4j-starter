package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Employee;
import org.neo4j.ogm.annotation.*;

/**
 * 业务归属于关系
 */

@RelationshipEntity(type = "业务归属于")
public class BussinessBelong extends BaseRelation{
    @EndNode
    private Employee employee;

    @StartNode
    private AccidentCase accidentCase;


    public BussinessBelong() { }

    public BussinessBelong(Employee employee, AccidentCase accidentCase) {
        this.employee = employee;
        this.accidentCase = accidentCase;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public AccidentCase getAccidentCase() {
        return accidentCase;
    }

    public void setAccidentCase(AccidentCase accidentCase) {
        this.accidentCase = accidentCase;
    }
}

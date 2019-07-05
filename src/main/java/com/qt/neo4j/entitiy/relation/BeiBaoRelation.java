package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Customer;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "是被保人")
public class BeiBaoRelation extends BaseRelation {
    @StartNode
    private Customer customer;

    @EndNode
    private AccidentCase accidentCase;

    public BeiBaoRelation() {
    }

    public BeiBaoRelation(Customer customer, AccidentCase accidentCase) {
        this.customer = customer;
        this.accidentCase = accidentCase;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccidentCase getAccidentCase() {
        return accidentCase;
    }

    public void setAccidentCase(AccidentCase accidentCase) {
        this.accidentCase = accidentCase;
    }
}
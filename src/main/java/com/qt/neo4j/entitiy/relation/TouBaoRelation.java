package com.qt.neo4j.entitiy.relation;


import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Customer;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "是投保人")
public class TouBaoRelation  {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Customer customer;

    @EndNode
    private AccidentCase accidentCase;

    public TouBaoRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TouBaoRelation(Customer customer, AccidentCase accidentCase) {
        this.customer = customer;
        this.accidentCase = accidentCase;
    }

    public TouBaoRelation(Long id, Customer customer, AccidentCase accidentCase) {
        this.id = id;
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


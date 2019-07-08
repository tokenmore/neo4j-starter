package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Customer;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "是领款人")
public class LingKuanRelation  {
    @StartNode
    private Customer customer;

    @EndNode
    private AccidentCase accidentCase;
    @Id
    @GeneratedValue
    private Long id;
    public LingKuanRelation() {
    }

    public LingKuanRelation(Customer customer, AccidentCase accidentCase, Long id) {
        this.customer = customer;
        this.accidentCase = accidentCase;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LingKuanRelation(Customer customer, AccidentCase accidentCase) {
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

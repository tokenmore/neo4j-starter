package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.Customer;
import com.qt.neo4j.entitiy.Telephone;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "使用")
public class UserTelRelation  {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Customer customer;
    @EndNode
    private Telephone telephone;

    public UserTelRelation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTelRelation(Customer customer, Telephone telephone) {
        this.customer = customer;
        this.telephone = telephone;
    }

    public UserTelRelation(Long id, Customer customer, Telephone telephone) {
        this.id = id;
        this.customer = customer;
        this.telephone = telephone;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }
}

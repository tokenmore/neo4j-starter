package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "使用")
public class UserTelRelation  extends BaseRelation{
    @StartNode
    private Customer customer;
    @EndNode
    private Telephone telephone;

    public UserTelRelation() {
    }

    public UserTelRelation(Customer customer, Telephone telephone) {
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

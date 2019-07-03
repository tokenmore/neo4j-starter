package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Customer extends BaseNode{
    private String customerId;
    private String customerName;

    @Relationship(type = "使用",direction = Relationship.OUTGOING)
    private List<Telephone> telephones;

    @Relationship(type = "是被保人",direction = Relationship.OUTGOING)
    private List<AccidentCase> beiBaoList;

    @Relationship(type = "是投保人",direction = Relationship.OUTGOING)
    private List<AccidentCase> touBaoList;
//
    @Relationship(type = "是报案人",direction = Relationship.OUTGOING)
    private List<AccidentCase> baoAnList;

    @Relationship(type = "是领款人",direction = Relationship.OUTGOING)
    private List<AccidentCase> lingKuanList;

    public Customer() {
    }

    public Customer(String customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }



    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

}

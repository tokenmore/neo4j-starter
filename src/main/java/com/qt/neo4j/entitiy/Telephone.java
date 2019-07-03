package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Telephone extends BaseNode{
    private String telId;

    public Telephone(String telnumber) {
        this.telId = telnumber;
    }

    public String getTelnumber() {
        return telId;
    }

    public void setTelnumber(String telnumber) {
        this.telId = telnumber;
    }

    @Relationship(type = "是报案电话",direction =Relationship.OUTGOING)
    private List<AccidentCase> accidentCaseLIst;

    @Relationship(type = "是领款电话",direction = Relationship.OUTGOING)
    private List<AccidentCase> accidentCases;

    @Override
    public String toString() {
        return "Telephone{" +
                "telnumber='" + telId + '\'' +
                '}';
    }

    public Telephone() {
    }
}

package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "治疗于")
public class RescueInRelation extends BaseRelation {
    @StartNode
    private AccidentCase accidentCase;

    @EndNode
    private Hospital hospital;

    public RescueInRelation() {
    }

    public RescueInRelation(AccidentCase accidentCase, Hospital hospital) {
        this.accidentCase = accidentCase;
        this.hospital = hospital;
    }

    public AccidentCase getAccidentCase() {
        return accidentCase;
    }

    public void setAccidentCase(AccidentCase accidentCase) {
        this.accidentCase = accidentCase;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}

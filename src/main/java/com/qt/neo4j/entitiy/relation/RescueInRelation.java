package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Hospital;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "治疗于")
public class RescueInRelation  {
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private AccidentCase accidentCase;

    @EndNode
    private Hospital hospital;

    public RescueInRelation() {
    }

    public RescueInRelation(Long id, AccidentCase accidentCase, Hospital hospital) {
        this.id = id;
        this.accidentCase = accidentCase;
        this.hospital = hospital;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

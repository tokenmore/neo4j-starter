package com.qt.neo4j.entitiy.relation;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Telephone;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "是报案电话")
public class BaoAnTelRelation extends BaseRelation{
    @StartNode
    private Telephone telephone;
    @EndNode
    private AccidentCase accidentCase;

    public BaoAnTelRelation() {
    }

    public BaoAnTelRelation(Telephone telephone, AccidentCase accidentCase) {
        this.telephone = telephone;
        this.accidentCase = accidentCase;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public AccidentCase getAccidentCase() {
        return accidentCase;
    }

    public void setAccidentCase(AccidentCase accidentCase) {
        this.accidentCase = accidentCase;
    }
}

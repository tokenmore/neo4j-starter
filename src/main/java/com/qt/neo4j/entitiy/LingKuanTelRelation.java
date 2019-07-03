package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "是领款电话")
public class LingKuanTelRelation extends BaseRelation {

    @StartNode
    private Telephone telephone;

    @EndNode
    private AccidentCase accidentCase;

    public LingKuanTelRelation(Telephone telephone, AccidentCase accidentCase) {
        this.telephone = telephone;
        this.accidentCase = accidentCase;
    }

    public LingKuanTelRelation() {
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

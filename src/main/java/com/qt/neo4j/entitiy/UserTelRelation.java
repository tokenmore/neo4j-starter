package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "使用")
public class UserTelRelation  extends BaseRelation{
    @StartNode
    private Customer starnode;
    @EndNode
    private Telephone endnode;

    public UserTelRelation() {
    }

    public UserTelRelation(Customer starnode, Telephone endnode) {
        this.starnode = starnode;
        this.endnode = endnode;
    }

    public Customer getStarnode() {
        return starnode;
    }

    public void setStarnode(Customer starnode) {
        this.starnode = starnode;
    }

    public Telephone getEndnode() {
        return endnode;
    }

    public void setEndnode(Telephone endnode) {
        this.endnode = endnode;
    }
}

package com.qt.neo4j.entitiy;


import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "是投保人")
public class TouBaoRelation extends BaseRelation {
    @StartNode
    private Customer startnode;

    @EndNode
    private AccidentCase endnode;

    public TouBaoRelation() {
    }

    public TouBaoRelation(Customer startnode, AccidentCase endnode) {
        this.startnode = startnode;
        this.endnode = endnode;
    }

    public Customer getStartnode() {
        return startnode;
    }

    public void setStartnode(Customer startnode) {
        this.startnode = startnode;
    }

    public AccidentCase getEndnode() {
        return endnode;
    }

    public void setEndnode(AccidentCase endnode) {
        this.endnode = endnode;
    }
}

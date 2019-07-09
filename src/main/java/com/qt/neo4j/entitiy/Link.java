package com.qt.neo4j.entitiy;

public class Link {
    private int source;
    private int target;
    private String relation;
    public Link() {
    }

    public Link(int source, int target, String relation) {
        this.source = source;
        this.target = target;
        this.relation = relation;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}

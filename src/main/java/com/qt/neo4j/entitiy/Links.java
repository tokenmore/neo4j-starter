package com.qt.neo4j.entitiy;

public class Links {

    private int source;
    private int target;
    private String relation;
    private int value;

    public Links(int source, int target, String relation, int value) {
        this.source = source;
        this.target = target;
        this.relation = relation;
        this.value = value;
    }


    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Links() {
    }

    public Links(int source, int target, String relation) {
        this.source = source;
        this.target = target;
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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "Links{" +
                "source=" + source +
                ", target=" + target +
                ", relation='" + relation + '\'' +
                ", value=" + value +
                '}';
    }
}

package com.qt.neo4j.entitiy;

public class Links {

    private int source;
    private int target;
    private String relation;
    private int color;

    public Links(int source, int target, String relation, int color) {
        this.source = source;
        this.target = target;
        this.relation = relation;
        this.color = color;
    }


    public Links() {
    }

    @Override
    public String toString() {
        return "Links{" +
                "source=" + source +
                ", target=" + target +
                ", relation='" + relation + '\'' +
                ", color=" + color +
                '}';
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

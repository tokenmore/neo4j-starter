package com.qt.neo4j.entitiy;

public class Link {
    private Long source;
    private Long target;
    private String relation;
    private int color;
    public Link() {
    }

    public Link(Long source, Long target, String relation, int color) {
        this.source = source;
        this.target = target;
        this.relation = relation;
        this.color = color;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public Long getTarget() {
        return target;
    }

    public void setTarget(Long target) {
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

    @Override
    public String toString() {
        return "Link{" +
                "source=" + source +
                ", target=" + target +
                ", relation='" + relation + '\'' +
                ", color=" + color +
                '}';
    }
}

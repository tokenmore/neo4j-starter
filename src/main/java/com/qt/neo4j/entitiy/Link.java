package com.qt.neo4j.entitiy;

public class Link {
    private Long sourceId;
    private Long targetId;
    private String relation;
    private int color;
    public Link() {
    }

    public Link(Long sourceId, Long targetId, String relation, int color) {
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.relation = relation;
        this.color = color;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
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

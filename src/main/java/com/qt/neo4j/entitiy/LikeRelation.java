package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "Like")
public class LikeRelation {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private BaseNode startNode;

    @EndNode
    private BaseNode endNode;

    @Property("reason")
    private String resaon;
    @Property("since")
    private Integer since;
    @Property("relationId")
    private Integer relationId;

    public LikeRelation() {
    }

    public LikeRelation(BaseNode startNode, BaseNode endNode, String resaon, Integer since, Integer relationId) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.resaon = resaon;
        this.since = since;
        this.relationId = relationId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BaseNode getStartNode() {
        return startNode;
    }

    public void setStartNode(BaseNode startNode) {
        this.startNode = startNode;
    }

    public BaseNode getEndNode() {
        return endNode;
    }

    public void setEndNode(BaseNode endNode) {
        this.endNode = endNode;
    }

    public String getResaon() {
        return resaon;
    }

    public void setResaon(String resaon) {
        this.resaon = resaon;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}

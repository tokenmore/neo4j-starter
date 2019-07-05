package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;

import java.util.List;

public class RelationResult {
    //存放node节点的list
    private List<Node> nodeList;
    //存放links的list
    private List<Links> linksList;

    public RelationResult() {
    }

    public RelationResult(List<Node> nodeList, List<Links> linksList) {
        this.nodeList = nodeList;
        this.linksList = linksList;
    }



    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Links> getLinksList() {
        return linksList;
    }

    public void setLinksList(List<Links> linksList) {
        this.linksList = linksList;
    }


}

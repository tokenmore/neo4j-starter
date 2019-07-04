package com.qt.neo4j.controller;

import com.qt.neo4j.dao.TouBaoRelationRepsitory;
import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.entitiy.relation.TouBaoRelation;
import com.qt.neo4j.utils.RelationResult;
import com.qt.neo4j.utils.SetRelationPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TouBaoPersonRelationController {
    @Autowired
    private TouBaoRelationRepsitory touBaoRelationRepsitory;

    @RequestMapping("/countAllTouBaoRelation")
    public int countAllTouBaoRelation(){
        return touBaoRelationRepsitory.countAllTouBaoRelations();
    }

    @RequestMapping("/findAllTouBaoRelation")
    public List<TouBaoRelation> findAllTouBaoRelation(){
        return touBaoRelationRepsitory.findAllTouBaoRelation();
    }

    @RequestMapping("/findAllTouBaoRelationByCustomerId")
    public HashMap<String,Object> findAllTouBaoRelationByCustomerId(@RequestParam("customerId")String customerId){
        System.out.println(customerId);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        List<TouBaoRelation> touBaoRelationByCustomerId = touBaoRelationRepsitory.findTouBaoRelationByCustomerId(customerId);
        RelationResult r = SetRelationPropertiesUtils.setTouBaoPersonPropertiesUtils(touBaoRelationByCustomerId, nodeList, linkList);
        map.put("nodes",r.getNodeList());
        map.put("links",r.getLinksList());
        return map;
    }
}

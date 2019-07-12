package com.qt.neo4j.controller;

import com.qt.neo4j.dao.*;
import com.qt.neo4j.entitiy.*;
import com.qt.neo4j.entitiy.relation.*;
import com.qt.neo4j.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ZNLPConstroller {
    @Autowired
    private LingKuanTelRelationRepsitory lingKuanTelRelationRepsitory;
    @Autowired
    private UseTelRepsitory useTelRepsitory;
    @Autowired
    private TouBaoRelationRepsitory touBaoRelationRepsitory;
    @Autowired
    private BussinessBelongRepsitory bussinessBelongRepsitory;
    @Autowired
    private BaoAnTelRelationRepsitory baoAnTelRelationRepsitory;


    /**
     * 获取业务员和案件的所有边
     */
    @RequestMapping("/getAlledges")
    @ResponseBody
    public Map<String, Object> getAllEdges() {
        List<BussinessBelong> belongList = bussinessBelongRepsitory.findAllBussinessBeLongs();
        Map map = new HashMap<String, Object>();
        List<Node> nodeList = new ArrayList<>();//用来存放案件
        List<Links> linksList = new ArrayList<>();//用来存放关系
        RelationResult result = SetRelationPropertiesUtils.setBussineeBelongPropertiesUtils(belongList, nodeList, linksList);
        map.put("nodes", result.getNodeList());
        map.put("links", result.getLinksList());
        return map;
    }


    @RequestMapping("/getAccAndCustomerAndTelPhone")
    @ResponseBody//获取客户和电话的关系以及客户和案件的关系
    public Map<String, Object> getAccAndCustomerAndTelPhone() {
        Map<String, Object> map = new HashMap();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new ArrayList<>();
        List<UserTelRelation> telRelation = useTelRepsitory.findAllTelRelation();
        List<TouBaoRelation> allTouBaoRelation = touBaoRelationRepsitory.findAllTouBaoRelation();
        RelationResult relationResult = SetRelationPropertiesUtils.setUseTelPropertiesUtils(telRelation, nodeList, linksList);
        RelationResult res = SetRelationPropertiesUtils.setTouBaoPersonPropertiesUtils(allTouBaoRelation, relationResult.getNodeList(), relationResult.getLinksList());
        map.put("nodes", res.getNodeList());
        map.put("links", res.getLinksList());
        return map;

    }

    @RequestMapping("/getUseTelAndBaoAnTel")//获取客户使用电话和案件报案电话{}
    @ResponseBody//客户使用电话，电话是报案电话
    public HashMap<String, Object> getUseTelAndBaoAnTel() {
        HashMap<String, Object> map = new HashMap<>();
        String telId = "15935421879";
        List<UserTelRelation> userTelRelations = useTelRepsitory.findUserTelRelationByTelId(telId);
        List<BaoAnTelRelation> baoAnTelRelationByTelId = baoAnTelRelationRepsitory.getBaoAnTelRelationByTelId(telId);
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new LinkedList<>();
        RelationResult r1 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelations, nodeList, linksList);
        RelationResult result = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils(baoAnTelRelationByTelId, r1.getNodeList(), r1.getLinksList());
        map.put("nodes", result.getNodeList());
        map.put("links", result.getLinksList());
        return map;
    }



    //客户使用电话，电话是报案电话、领款电话
    @RequestMapping("/getBaoAnAndLingKuanAndKeHuRelation")
    @ResponseBody
    public HashMap<String, Object> getBaoAnAndLingKuanAndKeHuRelation() {
        String telId = "13723185755";
        HashMap<String, Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new ArrayList<>();
        List<LingKuanTelRelation> lingKuanTelRelationBytelId = lingKuanTelRelationRepsitory.getLingKuanTelRelationBytelId(telId);
        List<BaoAnTelRelation> baoAnTelRelationByTelId = baoAnTelRelationRepsitory.getBaoAnTelRelationByTelId(telId);
        List<UserTelRelation> userTelRelationByTelId = useTelRepsitory.findUserTelRelationByTelId(telId);
        RelationResult r1 = SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils(lingKuanTelRelationBytelId, nodeList, linksList);
        RelationResult r2 = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils(baoAnTelRelationByTelId, r1.getNodeList(), r1.getLinksList());
        RelationResult r3 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelationByTelId, r2.getNodeList(), r2.getLinksList());
        map.put("nodes", r3.getNodeList());
        map.put("links", r3.getLinksList());
        return map;
    }

    //
}

package com.qt.neo4j.controller;

import com.qt.neo4j.dao.*;
import com.qt.neo4j.entitiy.Links;
import com.qt.neo4j.entitiy.Node;
import com.qt.neo4j.entitiy.relation.*;
import com.qt.neo4j.utils.Neo4jUtils;
import com.qt.neo4j.utils.RelationResult;
import com.qt.neo4j.utils.SetRelationPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TestController {
    @Autowired //客户-》案件 （是报案人）
    private BaoAnRelationRepsitory baoAnRelationRepsitory;
    @Autowired // 案件-》医院 （治疗于）
    private RescueRespository rescueRespository;
    @Autowired //电话-》案件 是报案电话
    private BaoAnTelRelationRepsitory baoAnTelRelationRepsitory;
    @Autowired // 客户-》电话 （使用）
    private UseTelRepsitory useTelRepsitory;
    @Autowired// 案件 =>业务员（业务归属于）
    private BussinessBelongRepsitory bussinessBelongRepsitory;
    @Autowired// 电话 =》 案件 (是领款电话)
    private LingKuanTelRelationRepsitory lingKuanTelRelationRepsitory;
    @Autowired // 客户 =》 案件 （是投保人）
    private TouBaoRelationRepsitory touBaoRelationRepsitory;
    @Autowired // 客户 =》 案件 （是被保人）
    private BeiBaoRelationRepsitory beiBaoRelationRepsitory;
    @Autowired // 客户=》 案件 （是领款人）
    private LingKuanRelationRepsitory lingKuanRelationRepsitory;
    @Autowired
    private AccidentCaseRepository accidentCaseRepository;

    @RequestMapping("/getCustomerIdByCaseId")
    public String getCustomerIdByCaseId(){
        return baoAnRelationRepsitory.getCustomerIdByCaseId("CHQ153000008413");
    }


    @RequestMapping("/getBaoAnTelRelationBycaseId")
    public List<BaoAnTelRelation> getBaoAnTelRelationBycaseId(){
        return baoAnTelRelationRepsitory.getBaoAnTelRelationBycaseId("QID153000004894");
    }

    @RequestMapping("/getlingkuanrelation")
    public RelationResult getlingkuanrelation(){
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        List<LingKuanTelRelation> list = lingKuanTelRelationRepsitory.getAllLingTelKuanRelations();
        return SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils(list,nodeList,linkList);
    }

    @RequestMapping("/getLingkuanrelationByTelId")
    public HashMap<String,Object> getLingkuanrelationByTelId(@RequestParam("telId")String telId){
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        List<LingKuanTelRelation> id = lingKuanTelRelationRepsitory.getLingKuanTelRelationBytelId(telId);
        RelationResult rs = SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils( id, nodeList, linkList);
        map.put("nodes",rs.getNodeList());
        map.put("links",rs.getLinksList());
        return map;
    }

    @RequestMapping("/real5/{caseId}")
    public HashMap<String,Object> real5(@PathVariable("caseId") String caseId ){
        System.out.println(caseId);
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        List<BaoAnRelation> baoAnRelationBycaseId = baoAnRelationRepsitory.findBaoAnRelationBycaseId(caseId);
        RelationResult r1 = SetRelationPropertiesUtils.setBaoAnPersonPropertiesUtils(baoAnRelationBycaseId, nodeList, linkList);
        String customerId = baoAnRelationRepsitory.getCustomerIdByCaseId(caseId);
        List<BaoAnTelRelation> baoAnTelRelationBycaseId = baoAnTelRelationRepsitory.getBaoAnTelRelationBycaseId(caseId);
        System.out.println(r1.getNodeList()+""+r1.getLinksList());
        RelationResult r2 = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils(baoAnTelRelationBycaseId, r1.getNodeList(), r1.getLinksList());
        List<UserTelRelation> userTelRelationByCustomerId = useTelRepsitory.findUserTelRelationByCustomerId(customerId);
        RelationResult r3 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelationByCustomerId, r2.getNodeList(), r2.getLinksList());
        List<BussinessBelong> allBussinessBeLongsBycaseId = bussinessBelongRepsitory.findAllBussinessBeLongsBycaseId(caseId);
        RelationResult r4 = SetRelationPropertiesUtils.setBussineeBelongPropertiesUtils(allBussinessBeLongsBycaseId, r3.getNodeList(), r3.getLinksList());
        List<RescueInRelation> rescueInRelationByCaseId = rescueRespository.findRescueInRelationByCaseId(caseId);
        RelationResult r5 = SetRelationPropertiesUtils.setRescueInPropertiesUtils(rescueInRelationByCaseId, r4.getNodeList(), r4.getLinksList());
        map.put("nodes",r5.getNodeList());
        map.put("links",r5.getLinksList());
        return map;

    }

    @RequestMapping("/getFullRelations/{caseId}")
    public HashMap<String,Object> getFullRelations(@PathVariable("caseId")String caseId){
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        List<LingKuanRelation> lingKuanRelationBycaseId = lingKuanRelationRepsitory.findLingKuanRelationBycaseId(caseId);
        RelationResult r1 = SetRelationPropertiesUtils.setLingKuanPersonPropertiesUtils(lingKuanRelationBycaseId, nodeList, linkList);
        List<BaoAnRelation> baoAnRelationBycaseId = baoAnRelationRepsitory.findBaoAnRelationBycaseId(caseId);
        RelationResult r2 = SetRelationPropertiesUtils.setBaoAnPersonPropertiesUtils(baoAnRelationBycaseId, r1.getNodeList(), r1.getLinksList());
        List<TouBaoRelation> touBaoRelationBycaseId = touBaoRelationRepsitory.findTouBaoRelationBycaseId(caseId);
        RelationResult r3 = SetRelationPropertiesUtils.setTouBaoPersonPropertiesUtils(touBaoRelationBycaseId, r2.getNodeList(), r2.getLinksList());
        List<BeiBaoRelation> beiBaoRelationBycaseId = beiBaoRelationRepsitory.findBeiBaoRelationBycaseId(caseId);
        RelationResult r4 = SetRelationPropertiesUtils.setBeiBaoPersonPropertiesUtils(beiBaoRelationBycaseId, r3.getNodeList(), r3.getLinksList());
        List<LingKuanTelRelation> lingKuanTelRelationByCaseId = lingKuanTelRelationRepsitory.getLingKuanTelRelationByCaseId(caseId);
        RelationResult r5 = SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils(lingKuanTelRelationByCaseId, r4.getNodeList(), r4.getLinksList());
        List<BaoAnTelRelation> baoAnTelRelationBycaseId = baoAnTelRelationRepsitory.getBaoAnTelRelationBycaseId(caseId);
        RelationResult r6 = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils(baoAnTelRelationBycaseId, r5.getNodeList(), r5.getLinksList());
        //通过案件Id获取对应的客户Id，然后通过客户Id，获取对应的手机号
        String customerId = touBaoRelationRepsitory.getCustomerIdByCaseId(caseId);
        List<UserTelRelation> userTelRelationByCustomerId = useTelRepsitory.findUserTelRelationByCustomerId(customerId);
        RelationResult r7 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelationByCustomerId, r6.getNodeList(), r6.getLinksList());
        List<BussinessBelong> allBussinessBeLongsBycaseId = bussinessBelongRepsitory.findAllBussinessBeLongsBycaseId(caseId);
        RelationResult r8 = SetRelationPropertiesUtils.setBussineeBelongPropertiesUtils(allBussinessBeLongsBycaseId, r7.getNodeList(), r7.getLinksList());
        List<RescueInRelation> rescueInRelationByCaseId = rescueRespository.findRescueInRelationByCaseId(caseId);
        RelationResult r9 = SetRelationPropertiesUtils.setRescueInPropertiesUtils(rescueInRelationByCaseId, r8.getNodeList(), r8.getLinksList());
        map.put("nodes",nodeList);
        map.put("links",linkList);
        return map;
    }

    @RequestMapping("/gettoubaorelation/{caseId}")
    public List<TouBaoRelation> touBaoRelations(@PathVariable("caseId") String caseId){
        return touBaoRelationRepsitory.findTouBaoRelationBycaseId(caseId);
    }

    @Autowired
    private Neo4jRepositity neo4jRepositity;

    @RequestMapping("/getAll")
    public List<HashMap<Object,Object>> getAllNodeAndRelation(){
        List<HashMap<Object,Object>> list = neo4jRepositity.getAllNodeAndRelation();
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        return list;
    }

    @RequestMapping("/getAllCaseId")
    public HashMap<String,Object> getAllCaseId(){
        HashMap<String,Object> result = new HashMap<>();
        List<String> allCaseId = accidentCaseRepository.getAllCaseId();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linkList = new ArrayList<>();
        RelationResult rs = new RelationResult(nodeList,linkList);
        for (int i=0;i<allCaseId.size();i++){
            rs = getFullRelationByCaseId(allCaseId.get(i),rs.getNodeList(),rs.getLinksList());
        }
        result.put("nodes",rs.getNodeList());
        result.put("links",rs.getLinksList());
        return result;
    }


    public  RelationResult getFullRelationByCaseId(String caseId,List<Node> nodeList,List<Links> linkList){
        HashMap<String,Object> map = new HashMap<>();
        List<LingKuanRelation> lingKuanRelationBycaseId = lingKuanRelationRepsitory.findLingKuanRelationBycaseId(caseId);
        RelationResult r1 = SetRelationPropertiesUtils.setLingKuanPersonPropertiesUtils(lingKuanRelationBycaseId, nodeList, linkList);
        List<BaoAnRelation> baoAnRelationBycaseId = baoAnRelationRepsitory.findBaoAnRelationBycaseId(caseId);
        RelationResult r2 = SetRelationPropertiesUtils.setBaoAnPersonPropertiesUtils(baoAnRelationBycaseId, r1.getNodeList(), r1.getLinksList());
        List<TouBaoRelation> touBaoRelationBycaseId = touBaoRelationRepsitory.findTouBaoRelationBycaseId(caseId);
        RelationResult r3 = SetRelationPropertiesUtils.setTouBaoPersonPropertiesUtils(touBaoRelationBycaseId, r2.getNodeList(), r2.getLinksList());
        List<BeiBaoRelation> beiBaoRelationBycaseId = beiBaoRelationRepsitory.findBeiBaoRelationBycaseId(caseId);
        RelationResult r4 = SetRelationPropertiesUtils.setBeiBaoPersonPropertiesUtils(beiBaoRelationBycaseId, r3.getNodeList(), r3.getLinksList());
        List<LingKuanTelRelation> lingKuanTelRelationByCaseId = lingKuanTelRelationRepsitory.getLingKuanTelRelationByCaseId(caseId);
        RelationResult r5 = SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils(lingKuanTelRelationByCaseId, r4.getNodeList(), r4.getLinksList());
        List<BaoAnTelRelation> baoAnTelRelationBycaseId = baoAnTelRelationRepsitory.getBaoAnTelRelationBycaseId(caseId);
        RelationResult r6 = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils(baoAnTelRelationBycaseId, r5.getNodeList(), r5.getLinksList());
        //通过案件Id获取对应的客户Id，然后通过客户Id，获取对应的手机号
        String customerId = touBaoRelationRepsitory.getCustomerIdByCaseId(caseId);
        List<UserTelRelation> userTelRelationByCustomerId = useTelRepsitory.findUserTelRelationByCustomerId(customerId);
        RelationResult r7 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelationByCustomerId, r6.getNodeList(), r6.getLinksList());
        List<BussinessBelong> allBussinessBeLongsBycaseId = bussinessBelongRepsitory.findAllBussinessBeLongsBycaseId(caseId);
        RelationResult r8 = SetRelationPropertiesUtils.setBussineeBelongPropertiesUtils(allBussinessBeLongsBycaseId, r7.getNodeList(), r7.getLinksList());
        List<RescueInRelation> rescueInRelationByCaseId = rescueRespository.findRescueInRelationByCaseId(caseId);
        RelationResult r9 = SetRelationPropertiesUtils.setRescueInPropertiesUtils(rescueInRelationByCaseId, r8.getNodeList(), r8.getLinksList());
        return r9;
    }

    @RequestMapping("/getAllRelations")
    public HashMap<String,Object> getAllRelations(){
        Neo4jUtils utils = new Neo4jUtils();
        String sql = "match p1=(n1:AccidentCase)-[r1:治疗于]->(n2:Hospital) with p1,n1,n2,r1 match p2=(n1)-[r2:业务归属于]->(n3:Employee) with p1,p2,n1,n2,n3,r1,r2 match p3=(n4:Customer)-[r3:是投保人]->(n1) with p1,p2,p3,n1,n2,n3,n4,r1,r2,r3 match p4=(n5:Telephone)-[r4:是报案电话]->(n1) with n1,n2,n3,n4,r1,r2,r3,n5,r4,p1,p2,p3,p4 match p5=(n5)-[r5:是领款电话]->(n1) with p1,p2,p3,p4,p5,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5 match p6=(n4)-[r6:使用]->(n5) with p1,p2,p3,p4,p5,p6,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6 match p7=(n4)-[r7:是被保人]->(n1) with p1,p2,p3,p4,p5,p6,p7 ,n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7 match p8=(n4)-[r8:是报案人]->(n1) with p1,p2,p3,p4,p5,p6,p7,p8, n1,n2,n3,n4,r1,r2,r3,n5,r4,r5,r6,r7,r8 match p9=(n4)-[r9:是领款人]->(n1)   return id(n1) as accidentId,n1.orgno as orgno,n1.pfmoney as pfmoney,n1.caseId as caseId,n1.qzflag as qzflag,id(n2) as hospitalId,n2.hospitaLevel as level,n2.hospitalId as hosId,n2.hospitalName as hosName,id(n3) as employeeId,n3.empId as empId,id(n4) as customerId,n4.customerId as custId,n4.customerName as customerName,id(n5) as telphoneId,n5.telId as telId,type(r1),type(r2),type(r3),type(r4),type(r5),type(r6),type(r7),type(r8),type(r9) limit 100";
        HashMap<String, Object> map = utils.matchNodesAndRelations(sql);
        return map;
    }
}

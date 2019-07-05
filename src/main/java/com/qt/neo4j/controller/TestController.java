package com.qt.neo4j.controller;

import com.qt.neo4j.dao.*;
import com.qt.neo4j.entitiy.*;
import com.qt.neo4j.entitiy.relation.*;
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
    public List<BaseRelation> getAllNodeAndRelation(){
        List<BaseRelation> list = neo4jRepositity.getAllNodeAndRelation();
        for (int i=0;i<list.size();i++){
            String name = list.get(i).getClass().getSimpleName();
           if(name.equals("TouBaoRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new TouBaoRelation());
               System.out.println(cast);
           }else if(name.equals("UserTelRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new UserTelRelation());
               System.out.println(cast);
           }else if(name.equals("BaoAnRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new BaoAnRelation());

               System.out.println(cast);
           }else if(name.equals("LingKuanRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new LingKuanRelation());
               System.out.println(cast);
           }else if(name.equals("BaoAnTelRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new BaoAnTelRelation());
               System.out.println(cast);
           }else if(name.equals("BeiBaoRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new BeiBaoRelation());
               System.out.println(cast);
           }else if(name.equals("BussinessBelong")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new BussinessBelong());
               System.out.println(cast);
           }else if(name.equals("LingKuanTelRelation")){
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new LingKuanTelRelation());
               System.out.println(cast);
           }else{
               Class<? extends BaseRelation> aClass = list.get(i).getClass();
               BaseRelation cast = aClass.cast(new RescueInRelation());
               System.out.println(cast);
           }
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
}

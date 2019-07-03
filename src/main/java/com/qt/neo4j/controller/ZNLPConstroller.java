package com.qt.neo4j.controller;

import com.qt.neo4j.dao.*;
import com.qt.neo4j.entitiy.*;
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
    @RequestMapping("/hello")
    @ResponseBody
    public  String hello(){
        return "hello";
    }

    /**
     * 获取所有 业务员和案件的关系
     * @return
     */
//    @RequestMapping("/getAllBussinessBelong")
//    @ResponseBody
//    public Map<String,Object> getAllBussinessBelong(){
//        List<BussinessBelong> allLists = bussinessBelongRepsitory.findAllBussinessBeLongs();
//        Map map = new HashMap<String,Object>();
//        List<AccidentCase> acclist = new ArrayList<>();//用来存放案件
//        List<Employee> emplist = new ArrayList<>();//用来存放所有业务员
//        List<Links> linksList = new ArrayList<>();//用来存放关系
//        int ff = 0;
//        for(int i=0;i<allLists.size();i++){
//            AccidentCase aCase = allLists.get(i).getAccidentCase();
//            acclist.add(aCase);
//            Employee employee = allLists.get(i).getEmployee();
//            emplist.add(employee);
//            int flag = ListUtils.judgeEmployeeExistInList(emplist,employee);
//            System.out.println("flag="+flag);
//
//            if(flag != -1){//如果flag！=-1，说明集合中存在着emp对象，这时不需要再重复插入，只需要获取该对象在集合中的下标
//                Links links = new Links(flag,i,"bussiness");
//                linksList.add(links);
//                ff++;
//            }else{//如果flag=-1说明集合中不存在该对象，那么集合中的元素数量加1，
//                Links links = new Links((i-ff),i,"bussiness");
//                linksList.add(links);
//            }
//            System.out.println("links:"+linksList);
//        }
//        System.out.println(emplist);
//        map.put("emp",emplist);
//        map.put("acc",acclist);
//        map.put("link",linksList);
//        return map;
//    }


    /**
     * 获取业务员和案件的所有边
     */
    @RequestMapping("/getAlledges")
    @ResponseBody
    public Map<String,Object> getAllEdges(){
        List<BussinessBelong> belongList = bussinessBelongRepsitory.findAllBussinessBeLongs();
        Map map = new HashMap<String,Object>();
        List<Node> nodeList = new ArrayList<>();//用来存放案件
        List<Links> linksList = new ArrayList<>();//用来存放关系
        int ff = 0;
        for(int i=0;i<belongList.size();i++){
            AccidentCase aCase = belongList.get(i).getAccidentCase();
            Node startNode = new Node();
            startNode.setLabelName(aCase.getCaseId());
            startNode.setAccTime(aCase.getAccTime());
            startNode.setCaseId(aCase.getCaseId());
            startNode.setOrgno(aCase.getOrgno());
            startNode.setPfmoney(aCase.getPfMoney());
            startNode.setQzflag(aCase.getQzflag());
            Employee employee = belongList.get(i).getEmployee();
            Node endNode = new Node();
            endNode.setLabelName(employee.getEmpId());
            endNode.setEmpid(employee.getEmpId());
            int flag = ListUtils.judgeNodeExistInList(nodeList,endNode);
            System.out.println("flag:"+flag);
            if(flag != -1){//如果flag！=-1，说明集合中存在着emp对象，这时不需要再重复插入，只需要获取该对象在集合中的下标
                Links links = new Links(i+1,flag,"bussiness",RandomUtil.getRandomColor());
                nodeList.add(startNode);
                linksList.add(links);
                ff++;
            }else{//如果flag=-1说明集合中不存在该对象，那么集合中的元素数量加1，
                Links links = new Links(i+1,(i-ff),"bussiness",RandomUtil.getRandomColor());
                nodeList.add(endNode);
                nodeList.add(startNode);
                linksList.add(links);
            }
        }
        map.put("nodes",nodeList);
        map.put("links",linksList);
        System.out.println(map.toString());

        return map;
    }


    @RequestMapping("/getAccAndCustomerAndTelPhone")
    @ResponseBody//获取客户和电话的关系以及客户和案件的关系
    public Map<String,Object> getAccAndCustomerAndTelPhone(){
        Map<String,Object> map = new HashMap();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new ArrayList<>();
        List<UserTelRelation> telRelation = useTelRepsitory.findAllTelRelation();
        List<TouBaoRelation> allTouBaoRelation = touBaoRelationRepsitory.findAllTouBaoRelation();
        int index = 0;
        for (int i=0;i<telRelation.size();i++){
            Node custNode = new Node();
            Customer customer = telRelation.get(i).getStarnode();
            custNode.setLabelName(customer.getCustomerName());
            custNode.setCustomerName(customer.getCustomerName());
            custNode.setCustomerId(customer.getCustomerId());
            custNode.setValue(RandomUtil.getRandomColor());
            nodeList.add(custNode);
            Node telNode = new Node();
            Telephone telephone = telRelation.get(i).getEndnode();
            telNode.setLabelName(telephone.getTelnumber());
            telNode.setTelId(telephone.getTelnumber());
            telNode.setValue(RandomUtil.getRandomColor());
            nodeList.add(telNode);
            Links links = new Links(i*2,i*2+1,"使用",RandomUtil.getRandomColor());
            linksList.add(links);
        }
        System.out.println("这个时候的nodelist的长度是"+nodeList.size());
        System.out.println("这个时候的linklist的长度是"+linksList.size());
        int linklen = linksList.size();
        int ff = 0;//记录有多少个重复出现的customer对象，初始值为0
        int nodelen = nodeList.size();
        for(int i=0;i<allTouBaoRelation.size();i++){
            Node custNode = new Node();
            //从查询结果集中获取到customer对象
            Customer customer = allTouBaoRelation.get(i).getStartnode();
            custNode.setLabelName(customer.getCustomerName());
            custNode.setCustomerName(customer.getCustomerName());
            custNode.setCustomerId(customer.getCustomerId());
            custNode.setValue(RandomUtil.getRandomColor());
            Node accNode = new Node();
            AccidentCase accidentCase = allTouBaoRelation.get(i).getEndnode();
            accNode.setLabelName(accidentCase.getCaseId());
            accNode.setAccTime(accidentCase.getAccTime());
            accNode.setCaseId(accidentCase.getCaseId());
            accNode.setOrgno(accidentCase.getOrgno());
            accNode.setPfmoney(accidentCase.getPfMoney());
            accNode.setQzflag(accidentCase.getQzflag());
            accNode.setValue(RandomUtil.getRandomColor());
            int flag = ListUtils.judgeCustomerExistInList(nodeList, custNode);
            if(flag != -1){
                nodeList.add(accNode);//此时nodeList的 length+1
                System.out.println("linklist的length:"+linksList.size());
                Links link = new Links(flag,nodelen,"是投保人",RandomUtil.getRandomColor());
                linksList.add(link);
                nodelen++;
                ff++;
            }else{
                nodeList.add(custNode);
                nodeList.add(accNode);
                Links link = new Links(nodelen-ff,nodelen+1-ff,"是投保人",RandomUtil.getRandomColor());
                linksList.add(link);
                nodelen = nodelen + 2 ;//新增了customer和accnode对象所以要加2
            }
        }
        System.out.println("这个时候的nodelist的长度是"+nodeList.size());
        System.out.println("这个时候的linklist的长度是"+linksList.size());
        map.put("nodes",nodeList);
        map.put("links",linksList);
        System.out.println(map);
        return map;

    }

    @RequestMapping("/getUseTelAndBaoAnTel")//获取客户使用电话和案件报案电话{}
    @ResponseBody//客户使用电话，电话是报案电话
    public HashMap<String,Object> getUseTelAndBaoAnTel(){
        HashMap<String,Object> map = new HashMap<>();
        String telId = "15935421879";
        List<UserTelRelation> userTelRelations = useTelRepsitory.findUserTelRelationByTelId(telId);
        List<BaoAnTelRelation> baoAnTelRelationByTelId = baoAnTelRelationRepsitory.getBaoAnTelRelationByTelId(telId);
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new LinkedList<>();
        int ff=0; //记录出现了多少个重复的值，没出现一次，ff+1，link的source和target需要用i-ff
        for(int i=0;i<userTelRelations.size();i++) {
            Node startnode = new Node();
            Node endnode = new Node();
            Customer customer = userTelRelations.get(i).getStarnode();
            startnode.setLabelName(customer.getCustomerName());
            startnode.setCustomerId(customer.getCustomerId());
            startnode.setCustomerName(customer.getCustomerName());
            startnode.setValue(RandomUtil.getRandomColor());
            Telephone telephone = userTelRelations.get(i).getEndnode();
            endnode.setLabelName(telephone.getTelnumber());
            endnode.setTelId(telephone.getTelnumber());
            endnode.setValue(RandomUtil.getRandomColor());
            int flag = ListUtils.judgeCustomerExistInList(nodeList, endnode);
            if (flag != -1) {
                nodeList.add(startnode);
                Links link = new Links(i - ff, flag, "使用", RandomUtil.getRandomColor());
                linksList.add(link);
                ff++;
            } else {
                nodeList.add(startnode);
                nodeList.add(endnode);
                Links link = new Links(i - ff , i - ff+1, "使用", RandomUtil.getRandomColor());
                linksList.add(link);
            }
        }
        System.out.println("目前为止nodelist的size为："+nodeList.size()+",linklist的size为："+linksList.size());
        int nodelen = nodeList.size();//得到nodelist的size
        int linklen = linksList.size();//得到linklist的size
        for (int i=0;i<baoAnTelRelationByTelId.size();i++){
            Node startNode = new Node();
            Node endNode = new Node();
            AccidentCase accidentCase = baoAnTelRelationByTelId.get(i).getAccidentCase();
            endNode.setLabelName(accidentCase.getCaseId());
            endNode.setAccTime(accidentCase.getAccTime());
            endNode.setPfmoney(accidentCase.getPfMoney());
            endNode.setQzflag(accidentCase.getQzflag());
            endNode.setValue(RandomUtil.getRandomColor());
            endNode.setOrgno(accidentCase.getOrgno());
            Telephone telephone = baoAnTelRelationByTelId.get(i).getTelephone();
            startNode.setLabelName(telephone.getTelnumber());
            startNode.setTelId(telephone.getTelnumber());
            int flag = ListUtils.judgeTelephoneExistInList(nodeList,startNode);
            if(flag!=-1){
                nodeList.add(endNode);
                Links link = new Links(flag,nodelen-ff+i,"是报案电话",RandomUtil.getRandomColor());
                linksList.add(link);
                ff++;
            }else{
                startNode.setValue(RandomUtil.getRandomColor());
                Links link = new Links(nodelen-ff+i,nodelen+i+1-ff,"是报案电话",RandomUtil.getRandomColor());
                nodeList.add(startNode);
                nodeList.add(endNode);
                linksList.add(link);
                nodelen = nodelen +2;
            }
            map.put("nodes",nodeList);
            map.put("links",linksList);
            System.out.println(map.toString());
        }
        return map;
    }


    //客户使用电话，电话是报案电话、领款电话
    @RequestMapping("/getBaoAnAndLingKuanAndKeHuRelation")
    @ResponseBody
    public HashMap<String,Object>  getBaoAnAndLingKuanAndKeHuRelation(){
        String telId = "13723185755";
        HashMap<String,Object> map = new HashMap<>();
        List<Node> nodeList = new ArrayList<>();
        List<Links> linksList = new ArrayList<>();
        List<LingKuanTelRelation> lingKuanTelRelationBytelId = lingKuanTelRelationRepsitory.getLingKuanTelRelationBytelId(telId);
        List<BaoAnTelRelation> baoAnTelRelationByTelId = baoAnTelRelationRepsitory.getBaoAnTelRelationByTelId(telId);
        List<UserTelRelation> userTelRelationByTelId = useTelRepsitory.findUserTelRelationByTelId(telId);
        RelationResult r1 = SetRelationPropertiesUtils.setLingKuanTelPropertiesUtils( lingKuanTelRelationBytelId, nodeList, linksList);
        RelationResult r2 = SetRelationPropertiesUtils.setBaoAnTelPropertiesUtils( baoAnTelRelationByTelId, r1.getNodeList(), r1.getLinksList());
        RelationResult r3 = SetRelationPropertiesUtils.setUseTelPropertiesUtils(userTelRelationByTelId, r2.getNodeList(), r2.getLinksList());
        map.put("nodes",r3.getNodeList());
        map.put("links",r3.getLinksList());
        return map;
    }

    //
}

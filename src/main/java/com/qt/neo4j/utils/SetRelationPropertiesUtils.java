package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.*;
import com.qt.neo4j.entitiy.relation.*;

import java.util.List;

public class SetRelationPropertiesUtils {
    /**
     * 该电话号码是领款电话
     * @param baseRelationList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setLingKuanTelPropertiesUtils(List<LingKuanTelRelation>
            baseRelationList,List<Node> nodeList,List<Links> linksList){
        int nodeLen ;
        for(int i=0;i<baseRelationList.size();i++){
            Telephone telephone = baseRelationList.get(i).getTelephone();
            AccidentCase accidentCase = baseRelationList.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setTelephoneProperty(telephone);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeTelephoneExistInList(nodeList, startNode);
            int af = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
               if(af == -1){
                   nodeLen = nodeList.size();
                   nodeList.add(endNode);
                   Links link = new Links(flag,nodeLen,"是领款电话",RandomUtil.getRandomColor());
                   linksList.add(link);
               }else{
                   nodeLen = nodeList.size();
                   Links link = new Links(flag,af,"是领款电话",RandomUtil.getRandomColor());
                   linksList.add(link);
               }
            }else{
                if(af == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"是领款电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,af,"是领款电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }

            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     * 该电话号码是报案电话
     * @param baoAnTelRelationList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setBaoAnTelPropertiesUtils(List<BaoAnTelRelation>
            baoAnTelRelationList,List<Node> nodeList,List<Links> linksList){
        int nodeLen;
        for(int i=0;i<baoAnTelRelationList.size();i++){
            Telephone telephone = baoAnTelRelationList.get(i).getTelephone();
            AccidentCase accidentCase = baoAnTelRelationList.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setTelephoneProperty(telephone);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeTelephoneExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 != -1){
                    Links link = new Links(flag,flag1,"是报案电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"是报案电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 != -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"是报案电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"是报案电话",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     *该客户是领款人
     * @param lingKuanRelation
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setLingKuanPersonPropertiesUtils(List<LingKuanRelation>
            lingKuanRelation,List<Node> nodeList,List<Links> linksList){
        int nodeLen;
        for(int i=0;i<lingKuanRelation.size();i++){
            Customer customer = lingKuanRelation.get(i).getCustomer();
            AccidentCase accidentCase = lingKuanRelation.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setCustomerProperty(customer);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeCustomerExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"是领款人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    Links link = new Links(flag,flag1,"是领款人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"是领款人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"是领款人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }
    /**
     *该客户是报案人
     * @param baoAnRelations
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setBaoAnPersonPropertiesUtils(List<BaoAnRelation>
            baoAnRelations,List<Node> nodeList,List<Links> linksList){
        int nodeLen;
        for(int i=0;i<baoAnRelations.size();i++){
            Customer customer = baoAnRelations.get(i).getCustomer();
            AccidentCase accidentCase = baoAnRelations.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setCustomerProperty(customer);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeCustomerExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"是报案人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    Links link = new Links(flag,flag1,"是报案人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen, nodeLen +1,"是报案人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"是报案人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }


    /**
     *该客户用了此手机号
     * @param userTelRelations
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setUseTelPropertiesUtils( List<UserTelRelation>
            userTelRelations,List<Node> nodeList,List<Links> linksList){
        int nodeLen  ;
        for(int i=0;i<userTelRelations.size();i++){
            Customer customer = userTelRelations.get(i).getCustomer();
            Telephone telephone = userTelRelations.get(i).getTelephone();
            Node startNode = SetObjectPropertyUtils.setCustomerProperty(customer);
            Node endNode = SetObjectPropertyUtils.setTelephoneProperty(telephone);
            int flag = ListUtils.judgeCustomerExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeTelephoneExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 != -1){
                    Links link = new Links(flag,flag1,"使用",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"使用",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 != -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"使用",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"使用",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     *该客户是投保人
     * @param touBaoRelationList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setTouBaoPersonPropertiesUtils( List<TouBaoRelation>
            touBaoRelationList,List<Node> nodeList,List<Links> linksList){
        int nodeLen ;
        for(int i=0;i<touBaoRelationList.size();i++){
            Customer customer = touBaoRelationList.get(i).getCustomer();
            AccidentCase accidentCase = touBaoRelationList.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setCustomerProperty(customer);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeCustomerExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"是投保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    Links link = new Links(flag,flag1,"是投保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
               if(flag1 == -1){
                   nodeLen = nodeList.size();
                   nodeList.add(startNode);
                   nodeList.add(endNode);
                   Links link = new Links(nodeLen,nodeLen+1,"是投保人",RandomUtil.getRandomColor());
                   linksList.add(link);
               }else{
                   nodeLen = nodeList.size();
                   nodeList.add(startNode);
                   Links link = new Links(nodeLen,flag1,"是投保人",RandomUtil.getRandomColor());
                   linksList.add(link);
               }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     *该客户是被保人
     * @param beiBaoRelationList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setBeiBaoPersonPropertiesUtils( List<BeiBaoRelation>
            beiBaoRelationList,List<Node> nodeList,List<Links> linksList){
        int nodeLen ;
        for(int i=0;i<beiBaoRelationList.size();i++){
            Customer customer = beiBaoRelationList.get(i).getCustomer();
            AccidentCase accidentCase = beiBaoRelationList.get(i).getAccidentCase();
            Node startNode = SetObjectPropertyUtils.setCustomerProperty(customer);
            Node endNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            int flag = ListUtils.judgeCustomerExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeAccidenCaseExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"是被保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    Links link = new Links(flag,flag1,"是被保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"是被保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"是被保人",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     * 该案件的当事人是在此医院接受治疗的
     * @param rescueInRelationList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setRescueInPropertiesUtils( List<RescueInRelation>
            rescueInRelationList,List<Node> nodeList,List<Links> linksList){
        int nodeLen  ;
        for(int i=0;i<rescueInRelationList.size();i++){
            AccidentCase accidentCase = rescueInRelationList.get(i).getAccidentCase();
            Hospital hospital = rescueInRelationList.get(i).getHospital();
            Node startNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            Node endNode = SetObjectPropertyUtils.setHospitalProperty(hospital);
            int flag = ListUtils.judgeAccidenCaseExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeHospitalExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"治疗于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    Links link = new Links(flag,flag1,"治疗于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"治疗于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"治疗于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

    /**
     * 该案件的业务归属于此业务员
     * @param bussinessBelongList
     * @param nodeList
     * @param linksList
     * @return
     */
    public static RelationResult setBussineeBelongPropertiesUtils(  List<BussinessBelong>
            bussinessBelongList,List<Node> nodeList,List<Links> linksList){
        int nodeLen  ;
        for(int i=0;i<bussinessBelongList.size();i++){
            AccidentCase accidentCase = bussinessBelongList.get(i).getAccidentCase();
            Employee employee = bussinessBelongList.get(i).getEmployee();
            Node startNode = SetObjectPropertyUtils.setAccidentProperty(accidentCase);
            Node endNode = SetObjectPropertyUtils.setEmployeeProperty(employee);
            int flag = ListUtils.judgeAccidenCaseExistInList(nodeList, startNode);
            int flag1 = ListUtils.judgeEmployeeExistInList(nodeList,endNode);
            if(flag != -1){
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(endNode);
                    Links link = new Links(flag,nodeLen,"业务归属于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    Links link = new Links(flag,flag1,"业务归属于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }else{
                if(flag1 == -1){
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    nodeList.add(endNode);
                    Links link = new Links(nodeLen,nodeLen+1,"业务归属于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }else{
                    nodeLen = nodeList.size();
                    nodeList.add(startNode);
                    Links link = new Links(nodeLen,flag1,"业务归属于",RandomUtil.getRandomColor());
                    linksList.add(link);
                }
            }
        }
        RelationResult result = new RelationResult();
        result.setNodeList(nodeList);
        result.setLinksList(linksList);
        return result;
    }

}

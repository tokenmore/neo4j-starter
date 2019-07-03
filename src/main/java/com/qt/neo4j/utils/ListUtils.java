package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.*;

import java.util.*;

public class ListUtils {
    /**
     * 对集合中相同对象进行去重
     * @param list
     * @return
     */
    public static List removeSameObject(List list){
        HashSet set = new HashSet(list);
        list.clear();
        list.addAll(set);
        return list;
    }


    //判断实体在arraylist是否存在，不存在返回-1
    public static int judgeNodeExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            String empid = list.get(i).getEmpid();
            if(empid !=null && empid.equals(node.getEmpid())){
                return i;
            }
        }
        return -1;
    }

    //判断员工对象是否存在
    public static int judgeEmployeeExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getEmpid()!= null && list.get(i).getEmpid().equals(node.getEmpid())){
                return i;
            }
        }
        return -1;
    }


    //判断客户对象是否存在
    public static int judgeCustomerExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCustomerId() != null && list.get(i).getCustomerId().equals(node.getCustomerId())){
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在范湖-1
        return -1;
    }

    //判断手机号对象是否存在
    public static int judgeTelephoneExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getTelId() != null && list.get(i).getTelId().equals(node.getTelId())){
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }

    //判断案件对象是否存在
    public static int judgeAccidenCaseExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCaseId()  != null && list.get(i).getCaseId().equals(node.getCaseId())){
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }

    //判断医院对象是否存在
    public static int judgeHospitalExistInList(List<Node> list, Node node){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getHospitalId() != null && list.get(i).getHospitalId().equals(node.getHospitalId())){
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }

}

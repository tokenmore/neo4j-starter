package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.*;

import java.util.*;

public class ListUtils {


    //判断节点在arraylist是否存在
    public static List<Node> judgeNodeExistInList(List<Node> list, Node node) {
        if (list.size() < 1) {
            list.add(node);
            return list;
        } else {
            for (int i = 0; i < list.size(); i++) {
                Long id = list.get(i).getId();
                if (id.equals(node.getId())) {
                    return list;
                }
            }
        }
        list.add(node);
        System.out.println("nodelist.size:"+list.size());
        return list;
    }


    public static List<Link> judgeLinkExistInList(List<Node> nodeList, Node node, List<Link> linkList) {
        for (int i = 0; i < nodeList.size(); i++) {
            Long id = nodeList.get(i).getId();
            if (id != null && id.equals(node.getId())) {
                nodeList.add(node);
            }
        }
        return linkList;
    }

    //判断员工对象是否存在
    public static int judgeEmployeeExistInList(List<Node> list, Node node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmpid() != null && list.get(i).getEmpid().equals(node.getEmpid())) {
                return i;
            }
        }
        return -1;
    }


    //判断客户对象是否存在
    public static int judgeCustomerExistInList(List<Node> list, Node node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCustomerId() != null && list.get(i).getCustomerId().equals(node.getCustomerId())) {
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在范湖-1
        return -1;
    }

    //判断手机号对象是否存在
    public static int judgeTelephoneExistInList(List<Node> list, Node node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTelId() != null && list.get(i).getTelId().equals(node.getTelId())) {
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }

    //判断案件对象是否存在
    public static int judgeAccidenCaseExistInList(List<Node> list, Node node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCaseId() != null && list.get(i).getCaseId().equals(node.getCaseId())) {
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }

    //判断医院对象是否存在
    public static int judgeHospitalExistInList(List<Node> list, Node node) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getHospitalId() != null && list.get(i).getHospitalId().equals(node.getHospitalId())) {
                return i; //如果存在，返回该对象在list中的下标
            }
        }
        //如果不存在返回-1
        return -1;
    }


    //
}

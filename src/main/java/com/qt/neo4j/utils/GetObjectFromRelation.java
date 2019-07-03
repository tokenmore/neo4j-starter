package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.AccidentCase;
import com.qt.neo4j.entitiy.Customer;
import com.qt.neo4j.entitiy.TouBaoRelation;

import java.util.HashMap;
import java.util.List;

public class GetObjectFromRelation {
    /**
     * 从投保人关系中获取案件和客户对象
     */
    public static HashMap<String,Object> getObjectfromTouBaoRelation(List<TouBaoRelation> touBaoRelationList,
                                                           HashMap<String,Object> map){
        for(int i=0;i<touBaoRelationList.size();i++){
            Customer customer = touBaoRelationList.get(i).getStartnode();

            AccidentCase accidentCase = touBaoRelationList.get(i).getEndnode();
        }
        return map;
    }
}

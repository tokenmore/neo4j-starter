package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.*;

public class SetObjectPropertyUtils {

    public static Node setCustomerProperty(Customer customer){
        Node node = new Node();
        node.setLabelName(customer.getCustomerName());
        node.setCustomerName(customer.getCustomerName());
        node.setCustomerId(customer.getCustomerId());
        node.setValue(RandomUtil.getRandomColor());
        return node;
    }

    public static Node setTelephoneProperty(Telephone telephone){
        Node node = new Node();
        node.setLabelName(telephone.getTelnumber());
        node.setTelId(telephone.getTelnumber());
        node.setValue(RandomUtil.getRandomColor());
        return node;
    }

    public static Node setAccidentProperty(AccidentCase accidentCase){
        Node node = new Node();
        node.setLabelName(accidentCase.getCaseId());
        node.setAccTime(accidentCase.getAccTime());
        node.setCaseId(accidentCase.getCaseId());
        node.setPfmoney(accidentCase.getPfMoney());
        node.setQzflag(accidentCase.getQzflag());
        node.setOrgno(accidentCase.getOrgno());
        node.setValue(RandomUtil.getRandomColor());
        return node;
    }


    //employee应该还有一个employeeId的属性，暂时没有注入，等以后再写
    public static Node setEmployeeProperty(Employee employee){
        Node node = new Node();
        node.setLabelName(employee.getEmpId());
        node.setEmpid(employee.getEmpId());
        node.setValue(RandomUtil.getRandomColor());
        return node;
    }

    public static Node setHospitalProperty(Hospital hospital){
        Node node = new Node();
        node.setLabelName(hospital.getHospitalName());
        node.setHospitalName(hospital.getHospitalName());
        node.setHospitaLevel(hospital.getHospitaLevel());
        node.setHospitalId(hospital.getHospitaLevel());
        node.setValue(RandomUtil.getRandomColor());
        return node;
    }
}

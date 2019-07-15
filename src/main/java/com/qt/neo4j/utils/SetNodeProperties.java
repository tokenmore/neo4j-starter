package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.Node;
import org.neo4j.driver.v1.Record;

public class SetNodeProperties {
    /**
     * 设置Accident的属性
     */
    public Node setAccident(Record record){
        Node accident = new Node();
        accident.setId(record.get("accidentId").asLong());
        accident.setOrgno(record.get("orgno").asString());
        accident.setPfmoney(record.get("pfmoney").asString());
        accident.setCaseId(record.get("caseId").asString());
        accident.setQzflag(record.get("qzflag").asString());
        accident.setLabelName(record.get("caseId").asString());
        accident.setColor(RandomUtil.getRandomColor());
        accident.setType("AccidentCase");
        return accident;
    }

    /**
     * 设置Hospital属性
     */
    public Node setHospital(Record record){
        Node hospital = new Node();
        hospital.setId(record.get("hospitalId").asLong());
        hospital.setHospitaLevel(record.get("level").asString());
        hospital.setHospitalId(record.get("hosId").asString());
        hospital.setHospitalName(record.get("hosName").asString());
        hospital.setLabelName(record.get("hosName").asString());
        hospital.setColor(RandomUtil.getRandomColor());
        hospital.setType("Hospital");
        return hospital;
    }

    /**
     * 设置Employee属性
     */
    public Node setEmployee(Record record){
        Node employee = new Node();
        employee.setId(record.get("employeeId").asLong());
        employee.setEmpid(record.get("empId").asString());
        employee.setLabelName(record.get("empId").asString());
        employee.setColor(RandomUtil.getRandomColor());
        employee.setType("Employee");
        return employee;
    }

    /**
     * 设置Customer
     * @param record
     * @return
     */
    public Node setCustomer(Record record){
        Node customer = new Node();
        customer.setId(record.get("customerId").asLong());
        customer.setCustomerId(record.get("custId").asString());
        customer.setCustomerName(record.get("customerName").asString());
        customer.setLabelName(record.get("customerName").asString());
        customer.setColor(RandomUtil.getRandomColor());
        customer.setType("Customer");
        System.out.println(customer);
        return  customer;
    }

    /**
     * 设置telephone
     * @param record
     * @return
     */
    public Node setTelephone(Record record){
        Node telephone = new Node();
        telephone.setId(record.get("telphoneId").asLong());
        telephone.setTelId(record.get("telId").asString());
        telephone.setLabelName(record.get("telId").asString());
        telephone.setColor(RandomUtil.getRandomColor());
        telephone.setType("Telephone");
        return  telephone;
    }
}

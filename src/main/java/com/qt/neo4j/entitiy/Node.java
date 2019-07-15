package com.qt.neo4j.entitiy;

public class Node {
    private Long id;
    private String labelName;
    private String type;

    private String customerId;
    private String customerName;
    private String telId;//电话号码
    private String hospitalId;
    private String hospitaLevel;
    private String hospitalName;
    private String empid;
    private String accTime;
    private String caseId;
    private String orgno;
    private String pfmoney;
    private String qzflag;
    private int color;

    public Node() {
    }

    public Node(String labelName, String type, String customerId, String customerName, String telId, String hospitalId, String hospitaLevel, String hospitalName, String empid, String accTime, String caseId, String orgno, String pfmoney, String qzflag, int color) {
        this.labelName = labelName;
        this.type = type;
        this.customerId = customerId;
        this.customerName = customerName;
        this.telId = telId;
        this.hospitalId = hospitalId;
        this.hospitaLevel = hospitaLevel;
        this.hospitalName = hospitalName;
        this.empid = empid;
        this.accTime = accTime;
        this.caseId = caseId;
        this.orgno = orgno;
        this.pfmoney = pfmoney;
        this.qzflag = qzflag;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitaLevel() {
        return hospitaLevel;
    }

    public void setHospitaLevel(String hospitaLevel) {
        this.hospitaLevel = hospitaLevel;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getAccTime() {
        return accTime;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno;
    }

    public String getPfmoney() {
        return pfmoney;
    }

    public void setPfmoney(String pfmoney) {
        this.pfmoney = pfmoney;
    }

    public String getQzflag() {
        return qzflag;
    }

    public void setQzflag(String qzflag) {
        this.qzflag = qzflag;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                ", type='" + type + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", telId='" + telId + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", hospitaLevel='" + hospitaLevel + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", empid='" + empid + '\'' +
                ", accTime='" + accTime + '\'' +
                ", caseId='" + caseId + '\'' +
                ", orgno='" + orgno + '\'' +
                ", pfmoney='" + pfmoney + '\'' +
                ", qzflag='" + qzflag + '\'' +
                ", color=" + color +
                '}';
    }
}

package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * 创建案件实体
 * AccidentCase
 */

@NodeEntity
public class AccidentCase {
    @Id
    @GeneratedValue
    private Long id;
    //案件ID
    private String caseId;
    //出险日期
    private String  accTime;
    //机构ID
    private String orgno;
    //反欺诈标志
    private  String qzflag;
    //赔付金额
    private String pfMoney;

//    @Relationship(type = "是投保人",direction = Relationship.INCOMING)
//    private Customer toubao;
//
//    @Relationship(type = "是被保人",direction = Relationship.INCOMING)
//    private Customer beibao;


    public AccidentCase() {
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getAccTime() {
        return accTime;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno;
    }

    public String getQzflag() {
        return qzflag;
    }

    public void setQzflag(String qzflag) {
        this.qzflag = qzflag;
    }

    public String getPfMoney() {
        return pfMoney;
    }

    public void setPfMoney(String pfMoney) {
        this.pfMoney = pfMoney;
    }

    public AccidentCase(String caseId, String accTime, String orgno, String qzflag, String pfMoney) {
        this.caseId = caseId;
        this.accTime = accTime;
        this.orgno = orgno;
        this.qzflag = qzflag;
        this.pfMoney = pfMoney;
    }

    @Override
    public String toString() {
        return "AccidentCase{" +
                "id=" + id +
                ", caseId='" + caseId + '\'' +
                ", accTime='" + accTime + '\'' +
                ", orgno='" + orgno + '\'' +
                ", qzflag='" + qzflag + '\'' +
                ", pfMoney='" + pfMoney + '\'' +
                '}';
    }
}

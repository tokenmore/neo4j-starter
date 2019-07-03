package com.qt.neo4j.entitiy;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Hospital extends BaseNode{
    //医院代码
    private String hospitalId;
    //医院级别
    private String hospitaLevel;
    //医院名字
    private String hospitalName;

    @Relationship(type = "治疗于",direction = Relationship.INCOMING)
    private List<AccidentCase> accList;
    public Hospital() {
    }

    public Hospital(String hospitalId, String hospitaLevel, String hospitalName) {
        this.hospitalId = hospitalId;
        this.hospitaLevel = hospitaLevel;
        this.hospitalName = hospitalName;
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

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId='" + hospitalId + '\'' +
                ", hospitaLevel='" + hospitaLevel + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                '}';
    }
}

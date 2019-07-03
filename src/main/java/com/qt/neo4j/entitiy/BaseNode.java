package com.qt.neo4j.entitiy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class BaseNode {

    @Id
    @GeneratedValue
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_EMPTY)
    private Long id;


    public BaseNode() {

    }
}

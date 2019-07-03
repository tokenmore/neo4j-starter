package com.qt.neo4j.service;

import com.qt.neo4j.dao.Neo4jRepositity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Neo4jService {

    @Autowired
    private Neo4jRepositity neo4jRepositity;

    public List<Object> getAllNodeLabels(){
        return neo4jRepositity.getALlNodes();
    }
}

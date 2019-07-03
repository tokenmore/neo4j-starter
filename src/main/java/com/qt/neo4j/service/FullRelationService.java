package com.qt.neo4j.service;

import com.qt.neo4j.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FullRelationService {
    @Autowired
    private TouBaoRelationRepsitory touBaoRelationRepsitory;
    @Autowired
    private BeiBaoRelationRepsitory beiBaoRelationRepsitory;
    @Autowired
    private LingKuanRelationRepsitory lingKuanRelationRepsitory;
    @Autowired
    private BaoAnTelRelationRepsitory baoAnTelRelationRepsitory;
    @Autowired
    private UseTelRepsitory useTelRepsitory;
    @Autowired
    private BaoAnRelationRepsitory baoAnRelationRepsitory;
    @Autowired
    private LingKuanTelRelationRepsitory lingKuanTelRelationRepsitory;
    @Autowired
    private BussinessBelongRepsitory bussinessBelongRepsitory;
    @Autowired
    private RescueRespository rescueRespository;

}

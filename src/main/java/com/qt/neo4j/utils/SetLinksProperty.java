package com.qt.neo4j.utils;

import com.qt.neo4j.entitiy.Link;
import org.neo4j.driver.v1.Record;

public class SetLinksProperty {

    public Link setLinksProperty(Record record, String sourceId,String targetId,String relation){
        Link link = new Link();
        link.setSourceId(record.get(sourceId).asLong());
        link.setTargetId(record.get(targetId).asLong());
        link.setRelation(relation);
        link.setColor(RandomUtil.getRandomColor());
        return  link;
    }

}

package com.myplace.app.places.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDate;

@Data
@RelationshipProperties
public class WillVisitRelationship {

    @Id
    @GeneratedValue
    private String id;

    @TargetNode
    private Place place;
    private String plannedDate;

    public WillVisitRelationship(final Place currentPlace, final String plannedDate) {
        this.place = currentPlace;
        this.plannedDate = plannedDate;
    }
}
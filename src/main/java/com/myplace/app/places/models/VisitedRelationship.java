package com.myplace.app.places.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@Builder
@AllArgsConstructor
@RelationshipProperties
public class VisitedRelationship {

    @Id
    @GeneratedValue
    private String id;

    @TargetNode
    private Place place;
    private String visitDate;

    public VisitedRelationship() {

    }
}
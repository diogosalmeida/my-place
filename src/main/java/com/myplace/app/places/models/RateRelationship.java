package com.myplace.app.places.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@Data
@Builder
@RelationshipProperties
public class RateRelationship {

    @Id
    @GeneratedValue
    private String id;

    @TargetNode
    private Place place;
    private Double rating;
    private LocalDateTime rateDate;
}
package com.myplace.app.places.models;

import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.time.LocalDateTime;

@Node
@Data
@Builder
@AllArgsConstructor
public class Place {
    @Id @GeneratedValue
    private Long id;

    @Property("name")
    private String name;
    private String description;
    private String location;
    private LocalDateTime createdAt;
}
package com.myplace.app.users.models;


import com.myplace.app.places.models.Place;
import com.myplace.app.places.models.RateRelationship;
import com.myplace.app.places.models.VisitedRelationship;
import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Node("User")
@Data
@Builder
@AllArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;

    @Property("name")
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    @Relationship(type = "VISITED")
    private Set<VisitedRelationship> visitedPlaces = new HashSet<>();

    @Relationship(type = "WILL_VISIT")
    private Set<Place> willVisitPlaces = new HashSet<>();

    @Relationship(type = "LIKES")
    private Set<Place> likedPlaces = new HashSet<>();

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private Set<User> following = new HashSet<>();

    public void follow(User user) {
        following.add(user);
    }
}

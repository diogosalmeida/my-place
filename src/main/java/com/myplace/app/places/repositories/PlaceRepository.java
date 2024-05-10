package com.myplace.app.places.repositories;

import com.myplace.app.places.models.Place;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PlaceRepository extends Neo4jRepository<Place, Long> {}
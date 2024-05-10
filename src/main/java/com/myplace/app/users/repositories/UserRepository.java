package com.myplace.app.users.repositories;

import com.myplace.app.users.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, Long> {
}

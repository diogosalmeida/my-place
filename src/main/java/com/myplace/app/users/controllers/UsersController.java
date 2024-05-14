package com.myplace.app.users.controllers;

import com.myplace.app.places.models.Place;
import com.myplace.app.places.models.VisitedRelationship;
import com.myplace.app.places.models.WillVisitRelationship;
import com.myplace.app.places.repositories.PlaceRepository;
import com.myplace.app.users.models.User;
import com.myplace.app.users.repositories.UserRepository;
import com.myplace.app.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(final @RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(final @PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(final @PathVariable Long id,
                                           final @RequestBody User userDetails) {

        return userService.updateUser(id, userDetails)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(final @PathVariable Long id) {
        if (userService.userExists(id)) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{placeId}/likes/{userId}")
    public ResponseEntity<String> addLike(final @PathVariable Long placeId,
                                          final @PathVariable Long userId) {

        userService.addLike(userId, placeId);
        return ResponseEntity.ok("Like added successfully");
    }

    @PostMapping("/{userId}/will-visit/{placeId}")
    public ResponseEntity<?> planVisit(final @PathVariable Long userId,
                                       final @PathVariable Long placeId,
                                       final @RequestBody String plannedDate) {

        userService.planVisit(userId, placeId, plannedDate);
        return ResponseEntity.ok("Visit planned successfully");
    }

    @PostMapping("/{userId}/visited/{placeId}")
    public ResponseEntity<?> visited(final @PathVariable Long userId,
                                     final @PathVariable Long placeId,
                                     final @RequestBody String plannedDate) {

        userService.visited(userId, placeId, plannedDate);
        return ResponseEntity.ok("Visit planned successfully");
    }


    @PostMapping("/{userId}/visit/{placeId}")
    public ResponseEntity<String> addVisit(final @PathVariable Long userId,
                                           final @PathVariable Long placeId,
                                           final @RequestBody String visitDate) {

        userService.addVisit(userId, placeId, visitDate);
        return ResponseEntity.ok("Visit added successfully");
    }

    @PostMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<?> followUser(final @PathVariable Long userId,
                                        final @PathVariable Long targetUserId) {
        userService.followUser(userId, targetUserId);
        return ResponseEntity.ok("Now following user " + targetUserId);
    }
}

package com.myplace.app.users.controllers;

import com.myplace.app.places.models.Place;
import com.myplace.app.places.models.VisitedRelationship;
import com.myplace.app.places.models.WillVisitRelationship;
import com.myplace.app.places.repositories.PlaceRepository;
import com.myplace.app.users.models.User;
import com.myplace.app.users.repositories.UserRepository;
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

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public UsersController(final UserRepository userRepository,
                           final PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(final @RequestBody User user) {
        final var savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(final @PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(final @PathVariable Long id,
                                           final @RequestBody User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    userRepository.save(user);
                    return ResponseEntity.ok(user);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{placeId}/likes/{userId}")
    public ResponseEntity<String> addLike(final @PathVariable Long placeId,
                                          final @PathVariable Long userId) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        if (user.isEmpty() || place.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Place not found");
        }

        final var currentUser = user.get();
        final var currentPlace = place.get();

        if (currentUser.getLikedPlaces().contains(currentPlace)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already likes this place");
        }

        currentUser.getLikedPlaces().add(currentPlace);
        userRepository.save(currentUser);

        return ResponseEntity.ok("Place liked successfully");
    }

    @PostMapping("/{userId}/will-visit/{placeId}")
    public ResponseEntity<?> planVisit(final @PathVariable Long userId,
                                       final @PathVariable Long placeId,
                                       final @RequestBody String plannedDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        if (user.isEmpty() || place.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Place not found");
        }

        final var currentUser = user.get();
        final var currentPlace = place.get();

        WillVisitRelationship willVisit = new WillVisitRelationship(currentPlace, plannedDate);
        currentUser.getWillVisitPlaces().add(willVisit.getPlace());
        userRepository.save(currentUser);

        return ResponseEntity.ok("Visit planned successfully");
    }

    @PostMapping("/{userId}/visited/{placeId}")
    public ResponseEntity<?> visited(final @PathVariable Long userId,
                                     final @PathVariable Long placeId,
                                     final @RequestBody String plannedDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        if (user.isEmpty() || place.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Place not found");
        }

        final var currentUser = user.get();
        final var currentPlace = place.get();

        WillVisitRelationship willVisit = new WillVisitRelationship(currentPlace, plannedDate);
        currentUser.getWillVisitPlaces().add(willVisit.getPlace());
        userRepository.save(currentUser);

        return ResponseEntity.ok("Visit planned successfully");
    }


    @PostMapping("/{userId}/visit/{placeId}")
    public ResponseEntity<String> addVisit(@PathVariable Long userId, @PathVariable Long placeId, @RequestBody String visitDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        if (user.isEmpty() || place.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Place not found");
        }

        final var currentUser = user.get();
        final var currentPlace = place.get();

        VisitedRelationship visit = new VisitedRelationship();
        visit.setPlace(currentPlace);
        visit.setVisitDate(visitDate);

        currentUser.getVisitedPlaces().add(visit);
        userRepository.save(currentUser);

        return ResponseEntity.ok("Visit added successfully");
    }
}

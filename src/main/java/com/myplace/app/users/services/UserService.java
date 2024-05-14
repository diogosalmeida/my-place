package com.myplace.app.users.services;

import com.myplace.app.places.models.Place;
import com.myplace.app.places.models.VisitedRelationship;
import com.myplace.app.places.models.WillVisitRelationship;
import com.myplace.app.places.repositories.PlaceRepository;
import com.myplace.app.users.models.User;
import com.myplace.app.users.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public UserService(final UserRepository userRepository,
                       final PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeRepository = placeRepository;
    }


    public Optional<User> getUserById(final Long id) {
        return userRepository.findById(id);
    }

    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public Optional<ResponseEntity<User>> updateUser(final Long id,
                                                     final User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    userRepository.save(user);
                    return ResponseEntity.ok(user);
                });
    }

    public void deleteUser(final Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public boolean userExists(final Long id) {
        return userRepository.existsById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public void addLike(final Long userId, final Long placeId) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        verifyUserAndPlace(user, place);

        final var currentUser = user.get();
        final var currentPlace = place.get();

        if (currentUser.getLikedPlaces().contains(currentPlace)) {
            throw new UserAlreadyLikesThisPlaceException("User already likes this place");
        }

        currentUser.getLikedPlaces().add(currentPlace);
        userRepository.save(currentUser);
    }

    private static void verifyUserAndPlace(Optional<User> user, Optional<Place> place) {
        if (user.isEmpty() || place.isEmpty()) {
            throw new UserOrPlaceException("User or Place not found");
        }
    }

    public void planVisit(final Long userId, final Long placeId, final String plannedDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        verifyUserAndPlace(user, place);

        final var currentUser = user.get();
        final var currentPlace = place.get();

        WillVisitRelationship willVisit = new WillVisitRelationship(currentPlace, plannedDate);
        currentUser.getWillVisitPlaces().add(willVisit.getPlace());
        userRepository.save(currentUser);
    }

    public void visited(final Long userId, final Long placeId, final String plannedDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        verifyUserAndPlace(user, place);

        final var currentUser = user.get();
        final var currentPlace = place.get();

        WillVisitRelationship willVisit = new WillVisitRelationship(currentPlace, plannedDate);
        currentUser.getWillVisitPlaces().add(willVisit.getPlace());
        userRepository.save(currentUser);
    }

    public void addVisit(final Long userId, final Long placeId, final String visitDate) {
        final var user = userRepository.findById(userId);
        final var place = placeRepository.findById(placeId);

        verifyUserAndPlace(user, place);
        final var currentUser = user.get();
        final var currentPlace = place.get();

        VisitedRelationship visit = new VisitedRelationship();
        visit.setPlace(currentPlace);
        visit.setVisitDate(visitDate);

        currentUser.getVisitedPlaces().add(visit);
        userRepository.save(currentUser);
    }

    public void followUser(final Long userId, final Long targetUserId) {
        final var user = userRepository.findById(userId);
        final var targetUser = userRepository.findById(targetUserId);

        if (user.isEmpty() || targetUser.isEmpty()) {
            throw new UserOrPlaceException("User or Place not found");
        }

        user.get().follow(targetUser.get());
        userRepository.save(user.get());
    }
}

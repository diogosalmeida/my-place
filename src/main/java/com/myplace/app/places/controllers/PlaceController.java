package com.myplace.app.places.controllers;

import com.myplace.app.places.models.Place;
import com.myplace.app.places.repositories.PlaceRepository;
import com.myplace.app.places.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceService placeService;

    public PlaceController(final PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Place> createPlace(final @RequestBody Place place) {
       return ResponseEntity.ok(placeService.createPlace(place));
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllPlaces() {
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(final @PathVariable Long id) {
        return placeService.getPlaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
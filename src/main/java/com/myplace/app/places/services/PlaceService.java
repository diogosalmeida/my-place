package com.myplace.app.places.services;


import com.myplace.app.places.models.Place;
import com.myplace.app.places.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {
    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public Optional<Place> getPlaceById(final Long id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(final Place place) {
        return placeRepository.save(place);
    }

    public Place updatePlace(final Place place) {
        return placeRepository.save(place);
    }

    public void deletePlace(final Long id) {
        placeRepository.deleteById(id);
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }
}

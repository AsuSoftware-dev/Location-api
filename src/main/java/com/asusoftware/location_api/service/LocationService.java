package com.asusoftware.location_api.service;

import com.asusoftware.location_api.model.Location;
import com.asusoftware.location_api.repository.LocationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    // Șterge cache-ul când locația este modificată
    @CacheEvict(value = "locations", key = "#id")
    public Location updateLocation(UUID id, Location updatedLocation) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + id));

        location.setStreet(updatedLocation.getStreet());
        location.setNumber(updatedLocation.getNumber());
        location.setCity(updatedLocation.getCity());
        location.setState(updatedLocation.getState());
        location.setPostalCode(updatedLocation.getPostalCode());
        location.setCountry(updatedLocation.getCountry());
        location.setLatitude(updatedLocation.getLatitude());
        location.setLongitude(updatedLocation.getLongitude());

        return locationRepository.save(location);
    }

    @Cacheable(value = "locations", key = "#id")
    public Location getLocationById(UUID id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + id));
    }

    @Cacheable(value = "locations", key = "#id")
    public void deleteLocation(UUID id) {
        if (!locationRepository.existsById(id)) {
            throw new EntityNotFoundException("Location not found with ID: " + id);
        }
        locationRepository.deleteById(id);
    }
}

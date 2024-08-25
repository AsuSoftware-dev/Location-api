package com.asusoftware.location_api.controller;

import com.asusoftware.location_api.model.Location;
import com.asusoftware.location_api.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return ResponseEntity.ok(createdLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable UUID id, @RequestBody Location updatedLocation) {
        Location location = locationService.updateLocation(id, updatedLocation);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable UUID id) {
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}

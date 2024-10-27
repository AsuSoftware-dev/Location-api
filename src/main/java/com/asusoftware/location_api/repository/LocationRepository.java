package com.asusoftware.location_api.repository;

import com.asusoftware.location_api.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    @Query(value = "SELECT * FROM locations l WHERE l.latitude BETWEEN ?2 AND ?4 AND l.longitude BETWEEN ?1 AND ?3", nativeQuery = true)
    List<Location> findPlacesWithinBounds(double swLng, double swLat, double neLng, double neLat);
}

package com.asusoftware.location_api.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.UUID;

@Data
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;
}

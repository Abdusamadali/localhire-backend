package com.abdus.localhirebackend.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Location {

    private String area;      // Sector, Market, Locality
    private String city;
    private String state;
    private String pincode;
}

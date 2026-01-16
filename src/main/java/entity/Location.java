package entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Location {

    private String area;      // Sector, Market, Locality
    private String city;
    private String state;
    private String pincode;
}

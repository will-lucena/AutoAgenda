package com.example.will.projetofinal.models;

public class Place {

    private String name;
    private String city;
    private String state;
    private String country;
    private String street;
    private long longitude;
    private long latitude;

    public Place(String name, String city, String state, String country, String street, long longitude, long latitude)
    {
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
        this.state = state;
        this.street = street;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public long getLatitude()
    {
        return latitude;
    }

    public long getLongitude()
    {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s/%s - %s", name, street, city, state, country);
    }
}

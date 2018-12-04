package com.example.will.projetofinal.models;

public class Place {

    private String name;
    private String city;
    private String state;
    private String country;
    private String street;
    private double longitude;
    private double latitude;

    public Place(String name, String city, String state, String country, String street, double longitude, double latitude)
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

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("%s - %s, %s/%s - %s", name, street, city, state, country);
    }

    public String getName()
    {
        return name;
    }
}

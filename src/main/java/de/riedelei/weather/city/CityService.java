package de.riedelei.weather.city;

import org.springframework.stereotype.Service;

// https://openweathermap.org/api/geocoding-api
@Service
public class CityService {
    private String placesUrl = "http://api.openweathermap.org/geo/1.0/direct?q=";

    public City getLonLatForCity(String city) {

    }
}

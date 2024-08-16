package de.riedelei.weather.city;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// https://openweathermap.org/api/geocoding-api
@Service
public class CityService {
    private String placesUrl = "http://api.openweathermap.org/geo/1.0/direct?q=";

    public City callCityData(String city) {
        RestTemplate restTemplate = new RestTemplate();
        setUrlWithLatLon(city);
        return restTemplate.getForObject(placesUrl, City.class);

    }

    public void setCity(String city) {

    }

    private void setUrlWithLatLon(String city) {
        this.placesUrl = this.placesUrl.concat(city).concat("&appid={c5b4da3f4057c95b48ffd41706fe58ea}");
    }
}

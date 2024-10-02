package de.riedelei.weather.weatherdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @CrossOrigin
    @GetMapping("weather")
    public Weather getWeatherForLatLon(@RequestParam long lon, @RequestParam long lat) throws JsonProcessingException {
        return weatherService.getWeatherDataFromOpenWeatherMap(lon, lat);
    }

    @CrossOrigin
    @GetMapping("weather")
    public List<Weather> getWeatherForCity(@RequestParam String city) {
        return weatherService.getWeatherComplete(city);
    }
}

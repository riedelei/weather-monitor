package de.riedelei.weather.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("city/{city}")
    public City getLatLonFromCity(@PathVariable String city) throws JsonProcessingException {
        var cityResponse = cityService.callCityData(city);
        return cityResponse;
    }

    @PostMapping
    public void setCity(@PathVariable String city) {

    }
}

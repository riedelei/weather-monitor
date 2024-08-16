package de.riedelei.weather.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public City getLatLonFromCity(@PathVariable String city) {
        var cityResponse = cityService.callCityData(city);
        return cityResponse;
    }

    @PostMapping
    public void setCity(@PathVariable String city) {

    }
}

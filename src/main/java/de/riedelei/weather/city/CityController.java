package de.riedelei.weather.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class CityController {

    @Autowired
    private CityService cityService;

    // @CrossOrigin
    // @GetMapping("city/{name}")
    // public List<City> getLatLonFromCity(@PathVariable String name) throws JsonProcessingException {
    //     var cityResponse = cityService.callCityData(name);
    //     return cityResponse;
    // }

    @CrossOrigin
    @GetMapping("city") //
    public List<City> getLatLonFromCity(@RequestParam String name) throws JsonProcessingException {
        var cityResponse = cityService.callCityData(name);
        return cityResponse;
    }

    @CrossOrigin
    @GetMapping("onecity/{name}")
    public List<City> getLatLonFromOneCity(@PathVariable String name) throws JsonProcessingException {
        var cityResponse = cityService.callOneCityData(name);
        return cityResponse;
    }

    @CrossOrigin
    @PostMapping("favoritecity")
    public void setFavoriteCity(@RequestParam String name, @RequestParam Double lat, @RequestParam Double lon) {
        cityService.setFavoriteCity(name, lat, lon);
    }

    @PostMapping
    public void setCity(@PathVariable String city) {

    }
}

package de.riedelei.weather.city;


import com.fasterxml.jackson.core.JsonProcessingException;
import de.riedelei.weather.MonitorApplication;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

/*
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = MonitorApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class CityServiceTest {

    @Autowired
    public CityService cityService;

    public CityServiceTest() { }

    @Test
    public void givenCity_whenCallCityService_LatLonNotNull() throws JsonProcessingException {

        List<City> cities = cityService.callCityData("Kempten");
        var city = new City();
        if(!cities.isEmpty())
        {
            city = cities.get(0);
        }
        Assert.assertTrue(city.getLat() != 0.0);
        Assert.assertTrue(city.getLon() != 0.0);
    }

    @Test
    public void givenCity_whenCallCityService_MoreThanOne() throws JsonProcessingException {

        List<City> cities = cityService.callCityData("Frankfurt");

        Assert.assertTrue(cities.size() > 1);
    }
}*/

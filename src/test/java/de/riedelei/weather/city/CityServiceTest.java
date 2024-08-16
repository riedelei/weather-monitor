package de.riedelei.weather.city;


import de.riedelei.weather.MonitorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


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
    public void givenCity_whenCallCitie_thenOk() {

        /*City city = cityService.callCityData("Nürnberg");
        Assert.assertFalse(false);*/
    }
}

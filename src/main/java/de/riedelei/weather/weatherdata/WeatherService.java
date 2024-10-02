package de.riedelei.weather.weatherdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.riedelei.weather.util.OpenWeatherConst;
import de.riedelei.weather.util.StringManipulator;
import de.riedelei.weather.util.WeatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}&units=metric&lang=de

    private final String mainUrl = "https://api.openweathermap.org/data/2.5/weather?lat=";

    private StringManipulator stringManipulator;

    private OpenWeatherConst openWeatherConst;

    private WeatherMapper weatherMapper;

    public WeatherService() {
        stringManipulator = new StringManipulator();
        openWeatherConst = new OpenWeatherConst();
        weatherMapper = new WeatherMapper();
    }

    public Weather getWeatherDataFromOpenWeatherMap(long lon, long lat) throws JsonProcessingException {
        setUrl(lat, lon);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(mainUrl, String.class);
        var responseString = response.getBody().toString();
        return weatherMapper.generateWeatherObject(responseString);
    }

    public List<Weather> getWeatherComplete(String city) {
        //gibt es das schon i der db

        // nein, anfrage an cityservice

        // lan lat an openweather weitergaben
        return new ArrayList<Weather>();
    }

    private void setUrl(long lat, long lon) {
        stringManipulator.addVariableToString(mainUrl, String.valueOf(lat));
        stringManipulator.addVariableToString(mainUrl, "&lon="+lon);
        stringManipulator.addVariableToString(mainUrl, "&appid="+ openWeatherConst.APIKEY);
        stringManipulator.addVariableToString(mainUrl, "&units="+ OpenWeatherConst.UNIT.METRIC.name().toLowerCase());
        stringManipulator.addVariableToString(mainUrl, "&lang="+ OpenWeatherConst.LANGUAGE.DE.name().toLowerCase());
    }
}

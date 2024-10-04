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

    private String mainUrl = "https://api.openweathermap.org/data/2.5/weather?lat=";

    private StringManipulator stringManipulator;

    private OpenWeatherConst openWeatherConst;

    private WeatherMapper weatherMapper;

    public WeatherService() {
        stringManipulator = new StringManipulator(mainUrl);
        openWeatherConst = new OpenWeatherConst();
        weatherMapper = new WeatherMapper();
    }

    public Weather getWeatherDataFromOpenWeatherMap(String lon, String lat) throws JsonProcessingException {
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

    private void setUrl(String lat, String lon) {
        stringManipulator.addVariableToString(lat);
        stringManipulator.addVariableToString("&lon="+lon);
        stringManipulator.addVariableToString("&appid="+ openWeatherConst.APIKEY);
        stringManipulator.addVariableToString("&units="+ OpenWeatherConst.UNIT.METRIC.name().toLowerCase());
        stringManipulator.addVariableToString("&lang="+ OpenWeatherConst.LANGUAGE.DE.name().toLowerCase());
        mainUrl = stringManipulator.getUrl();
    }
}

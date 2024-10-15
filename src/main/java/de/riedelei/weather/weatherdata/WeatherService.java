package de.riedelei.weather.weatherdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.riedelei.weather.city.CityService;
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

    private Weather weather;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private CityService cityService;


    public WeatherService() {
        stringManipulator = new StringManipulator(mainUrl);
        openWeatherConst = new OpenWeatherConst();
        weatherMapper = new WeatherMapper();
        weather = new Weather();
    }

    public Weather getWeatherDataFromOpenWeatherMap(String lon, String lat) throws JsonProcessingException {
        setUrl(lat, lon);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(mainUrl, String.class);
        var responseString = response.getBody().toString();
        weather = weatherMapper.generateWeatherObject(responseString);
        //storeWeatherInDb();
        return weather;
    }

    public List<Weather> getWeatherComplete(String city) throws JsonProcessingException {
        var listCity = cityService.callCityData(city);
        var weatherList = new ArrayList<Weather>();

        if(listCity.size() == 1) {
            var oneCity = listCity.get(0);
            setUrl(String.valueOf(oneCity.getLat()), String.valueOf(oneCity.getLon()));

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(mainUrl, String.class);
            var responseString = response.getBody().toString();
            var weatherObject = weatherMapper.generateWeatherObject(responseString);

            weatherList.add(weatherObject);

        }


        for(var c: cityService.callCityData(city)) {

        }

        // nein, anfrage an cityservice

        // lan lat an openweather weitergaben
        return weatherList;
    }

    public void storeWeatherInDb() {
        weatherRepository.save(weather);
    }

    public Weather getWeatherFromDB(String lon, String lat) {
        var llon = Double.valueOf(lon);
        var llat = Double.valueOf(lat);
        return null;//weatherRepository.findWeatherByLonLat(llat, llon);
    }

    private void setUrl(String lat, String lon) {
        stringManipulator.addVariableToString(lat);
        stringManipulator.addVariableToString("&lon="+lon);
        stringManipulator.addVariableToString("&appid="+ OpenWeatherConst.APIKEY2);
        stringManipulator.addVariableToString("&units="+ OpenWeatherConst.UNIT.METRIC.name().toLowerCase());
        stringManipulator.addVariableToString("&lang="+ OpenWeatherConst.LANGUAGE.DE.name().toLowerCase());
        mainUrl = stringManipulator.getUrl();
    }
}

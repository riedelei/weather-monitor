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

        if(OpenWeatherConst.useDb == false) {
            setUrl(lat, lon);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(mainUrl, String.class);
            var responseString = response.getBody();
            weather = weatherMapper.generateWeatherObject(responseString);

            return weather;
        }
        else {
            return this.weatherRepository.findByLatAndLon(Float.parseFloat(lat), Float.parseFloat(lon));
        }
    }

    public Weather getWeatherForFavoriteCity() throws JsonProcessingException {
        var favoriteCity = cityService.getFavoriteCity();
        if(favoriteCity != null) {
            return getWeatherDataFromOpenWeatherMap(favoriteCity.getLon().toString(), favoriteCity.getLat().toString());
        }
        return null;
    }

    public List<Weather> getWeatherComplete(String city) throws JsonProcessingException {
        var listCity = cityService.callCityData(city);
        var weatherList = new ArrayList<Weather>();

        if(!OpenWeatherConst.useDb) {

            if (listCity.size() == 1) {
                var oneCity = listCity.get(0);
                setUrl(String.valueOf(oneCity.getLat()), String.valueOf(oneCity.getLon()));

                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> response = restTemplate.getForEntity(mainUrl, String.class);
                var responseString = response.getBody();
                var weatherObject = weatherMapper.generateWeatherObject(responseString);

                weatherList.add(weatherObject);

            }

            return weatherList;
        }
        else {
            return this.weatherRepository.findByCity(city);
        }
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

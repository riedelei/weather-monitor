package de.riedelei.weather.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.riedelei.weather.util.CityMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;

// https://openweathermap.org/api/geocoding-api
@Service
public class CityService {
    private String placesUrl = "http://api.openweathermap.org/geo/1.0/direct?q=";

    private String jasonString = "[{\"name\":\"Nuremberg\",\"local_names\":{\"es\":\"Núremberg\",\"sk\":\"Norimberg\",\"sr\":\"Нирнберг\",\"ko\":\"뉘른베르크\",\"en\":\"Nuremberg\",\"uk\":\"Нюрнберг\",\"ca\":\"Nuremberg\",\"nl\":\"Neurenberg\",\"la\":\"Norimberga\",\"de\":\"Nürnberg\",\"cs\":\"Norimberk\",\"fr\":\"Nuremberg\",\"ar\":\"نورنبرغ\",\"zh\":\"纽伦堡\",\"pl\":\"Norymberga\",\"hr\":\"Nürnberg\",\"it\":\"Norimberga\",\"fa\":\"نورنبرگ\",\"th\":\"เนือร์นแบร์ก\",\"hy\":\"Նյուրնբերգ\",\"pt\":\"Nuremberga\",\"he\":\"נירנברג\",\"lt\":\"Niurnbergas\",\"ka\":\"ნიურნბერგი\",\"ur\":\"نورنبرگ\",\"be\":\"Нюрнберг\",\"fi\":\"Nürnberg\",\"gl\":\"Nürnberg\",\"ja\":\"ニュルンベルク\",\"mk\":\"Нирнберг\",\"lv\":\"Nirnberga\",\"ru\":\"Нюрнберг\",\"el\":\"Νυρεμβέργη\"},\"lat\":49.453872,\"lon\":11.077298,\"country\":\"DE\",\"state\":\"Bavaria\"}]";
    public City callCityData(String city) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        setUrlWithLatLon(city);

        ResponseEntity<String> response
                 = restTemplate.getForEntity(placesUrl, String.class);
        var responseString = response.getBody().toString();
        var cityMapper = new CityMapper();
        return cityMapper.mapStringToCity(responseString);
    }

    private void setUrlWithLatLon(String city) {
        this.placesUrl = this.placesUrl.concat(city).concat("&appid=c5b4da3f4057c95b48ffd41706fe58ea");
    }
}

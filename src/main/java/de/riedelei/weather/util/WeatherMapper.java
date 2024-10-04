package de.riedelei.weather.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.riedelei.weather.weatherdata.Weather;
import de.riedelei.weather.weatherdata.WeatherCondition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherMapper {

    private String jsonText =
            """ 
                       {
                       "coord": {
                          "lon": 7.367,
                          "lat": 45.133
                       },
                       "weather": [
                          {
                             "id": 501,
                             "main": "Rain",
                             "description": "moderate rain",
                             "icon": "10d"
                          },
                    {
                             "id": 502,
                             "main": "Sun",
                             "description": "sunny",
                             "icon": "01n"
                          }
                       ],
                       "base": "stations",
                       "main": {
                          "temp": 284.2,
                          "feels_like": 282.93,
                          "temp_min": 283.06,
                          "temp_max": 286.82,
                          "pressure": 1021,
                          "humidity": 60,
                          "sea_level": 1021,
                          "grnd_level": 910
                       },
                       "visibility": 10000,
                       "wind": {
                          "speed": 4.09,
                          "deg": 121,
                          "gust": 3.47
                       },
                       "rain": {
                          "1h": 2.73
                       },
                       "clouds": {
                          "all": 83
                       },
                       "dt": 1726660758,
                       "sys": {
                          "type": 1,
                          "id": 6736,
                          "country": "IT",
                          "sunrise": 1726636384,
                          "sunset": 1726680975
                       },
                       "timezone": 7200,
                       "id": 3165523,
                       "name": "Province of Turin",
                       "cod": 200
                    }""";
    public Weather generateWeatherObject(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        return Weather.builder().id(1L)
                .visibility(jsonNode.get("visibility").asLong())
                .city(jsonNode.get("name").asText())
                .windSpeed(getValueFromJsonAsDouble(jsonNode, "wind", "speed"))
                .rain(getValueFromJsonAsDouble(jsonNode, "rain", "1h"))
                .sunset(getValueFromJsonAsLong(jsonNode, "sys", "sunset"))
                .sunrise(getValueFromJsonAsLong(jsonNode, "sys", "sunrise"))
                .temp(getValueFromJsonAsDouble(jsonNode, "main", "temp"))
                .feels_like(getValueFromJsonAsDouble(jsonNode, "main", "feels_like"))
                .temp_max(getValueFromJsonAsDouble(jsonNode, "main", "temp_max"))
                .temp_min(getValueFromJsonAsDouble(jsonNode, "main", "temp_min"))
                .humidity(getValueFromJsonAsInt(jsonNode, "main", "humidity"))
                .pressure(getValueFromJsonAsInt(jsonNode, "main", "pressure"))
                .sea_level(getValueFromJsonAsInt(jsonNode, "main", "sea_level"))
                .grnd_level(getValueFromJsonAsInt(jsonNode, "main", "grnd_level"))
                .clouds(getValueFromJsonAsInt(jsonNode, "clouds", "all"))
                .weatherCondition(extractArrayData(jsonNode))
                .build();
    }

    private static List<WeatherCondition> extractArrayData(final JsonNode jsonNode) {
        var arrayList = new ArrayList<WeatherCondition>();
        if(jsonNode.get("weather")!= null) {
            for (Iterator<JsonNode> iter = jsonNode.get("weather").elements(); iter.hasNext(); ) {
                final var ele = iter.next();

                if (ele != null) {
                    WeatherCondition weatherCondition = new WeatherCondition();
                    weatherCondition.setMain(ele.get("main").asText());
                    weatherCondition.setIcon("https://openweathermap.org/img/wn/"+ele.get("icon").asText()+"@2x.png");
                    weatherCondition.setDescription(ele.get("description").asText());
                    arrayList.add(weatherCondition);
                }
            }
        }
        return arrayList;
    }

    private static double getValueFromJsonAsDouble(final JsonNode jsonNode, final String rain, final String fieldName) {
        if (jsonNode.get(rain) != null && jsonNode.get(rain).get(fieldName) != null) {
            return jsonNode.get(rain).get(fieldName).asDouble();
        }
        return 0.0;
    }

    private static int getValueFromJsonAsInt(final JsonNode jsonNode, final String rain, final String fieldName) {
        if (jsonNode.get(rain) != null && jsonNode.get(rain).get(fieldName) != null) {
            return jsonNode.get(rain).get(fieldName).asInt();
        }
        return 0;
    }

    private static String getValueFromJsonAsString(final JsonNode jsonNode, final String fieldName) {

        return "";
    }

    private static long getValueFromJsonAsLong(final JsonNode jsonNode, final String rain, final String fieldName) {
        if (jsonNode.get(rain) != null && jsonNode.get(rain).get(fieldName) != null) {
            return jsonNode.get(rain).get(fieldName).asLong();
        }
        return 0L;
    }
}

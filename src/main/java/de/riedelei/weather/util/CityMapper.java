package de.riedelei.weather.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.riedelei.weather.city.City;

public class CityMapper {

    public City mapStringToCity(String scity) throws JsonProcessingException {

        scity = cutString(scity);
        var objectMapper =  new ObjectMapper();
        var jasonRootNode = objectMapper.readTree(scity);

        var slocalName = getLocalName(jasonRootNode.get("local_names"));
        var city = City.builder()
                .lon(jasonRootNode.get("lon").asDouble())
                .lat(jasonRootNode.get("lat").asDouble())
                .state(jasonRootNode.get("state") == null ? "" : jasonRootNode.get("state").asText())
                .name(jasonRootNode.get("name").asText())
                .localName(slocalName)
                .country(jasonRootNode.get("country").asText()).build();
        return city;
    }

    private String getLocalName(JsonNode jsonNode) {
        return jsonNode.get("de").asText();
    }

    private String cutString(String scity){
        return scity.substring(1, scity.length() - 1);
    }
}

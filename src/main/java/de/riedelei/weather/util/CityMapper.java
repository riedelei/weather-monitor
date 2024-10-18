package de.riedelei.weather.util;

import de.riedelei.weather.city.City;
import de.riedelei.weather.city.FavoriteCity;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityMapper {

    private List<City> cities;

    private Random random;

    public CityMapper() {
        cities = new ArrayList<>();
        this.random = new Random();
    }

    public List<City> mapStringToCity(String scity) {

        var jsonArray = new JSONArray(scity);

        for(int i = 0; i < jsonArray.length(); i++) {
            var o = jsonArray.getJSONObject(i);

            var city = City.builder()
                    .lon(o.getDouble("lon"))
                    .lat(o.getDouble("lat"))
                    .state(o.getString("state"))
                    .name(o.getString("name"))
                    .localName(o.isNull("local_names") ? "" : o.getJSONObject("local_names").getString("de"))
                    .country(o.getString("country")).build();
            city.setId(Math.absExact(this.random.nextLong()));
            cities.add(city);
        }

        return cities;
    }

    public FavoriteCity generateFavoriteCity(String name, Double lat, Double lon) {
        if(lat.isNaN()) lat = 0.0;
        if(lon.isNaN()) lon = 0.0;
        return FavoriteCity.builder().cityName(name).lat(lat).lon(lon).build();
    }
}

package de.riedelei.weather.util;

import de.riedelei.weather.city.City;

import java.util.ArrayList;
import java.util.List;

public class CityMock {

    public List<City> getAListOfCities()
    {
        var list = new ArrayList<City>();

        var city1 =  City.builder()
                .country("Deutschland")
                .name("Frankfurt am Main")
                .localName("Frankfurt")
                .state("Hessen")
                .lon(12.12)
                .lat(13.13)
                .Id(1L)
                .build();

        var city2 =  City.builder()
                .country("Deutschland")
                .name("Frankfurt an der Oder")
                .localName("Frankfurt")
                .state("Mecklenburg Vorpommern")
                .lon(21.12)
                .lat(31.13)
                .Id(2L)
                .build();
        list.add(city1);
        list.add(city2);
        return list;
    }
}

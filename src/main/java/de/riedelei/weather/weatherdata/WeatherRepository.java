package de.riedelei.weather.weatherdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Double> {
    public Optional<Weather> findByCity(String city);

    //public Weather findWeatherByLonLat(Double lat, Double lon);
}

package de.riedelei.weather.weatherdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    public Optional<Weather> findByCity(String city);

    public Weather findWeatherByLonLat(Long lat, Long lon);
}

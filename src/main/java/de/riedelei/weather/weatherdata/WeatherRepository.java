package de.riedelei.weather.weatherdata;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    public List<Weather> findByCity(String city);

    public Weather findByLatAndLon(float Lat, float Lon);
}

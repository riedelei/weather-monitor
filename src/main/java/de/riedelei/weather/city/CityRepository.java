package de.riedelei.weather.city;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, String> {

    public List<City> findCitiesByName(String name) ;
}

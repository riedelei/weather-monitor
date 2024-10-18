package de.riedelei.weather.city;

import org.springframework.data.repository.CrudRepository;

public interface FavoriteCityRepository extends CrudRepository<FavoriteCity, String> {
}

package de.riedelei.weather.weatherdata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Wind {
    @Id
    private Long Id;

    private double speed;
    private double deg;
    private double gust;

}

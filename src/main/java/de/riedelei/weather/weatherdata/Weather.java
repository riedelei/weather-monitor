package de.riedelei.weather.weatherdata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Weather {
    @Id
    private Long Id;
    @Column(name = "weather_id")
    private int weatherId;
    private String main;
    private String description;
    private String icon;
    private double temp;
    @Column(name = "feels_like")
    private double feelsLike;
    @Column(name = "temp_min")
    private double tempMin;
    @Column(name = "temp_max")
    private double tempMax;
    private double pressure;
    private double humidiy;
    @Column(name = "wind_id")
    private int windId;
}

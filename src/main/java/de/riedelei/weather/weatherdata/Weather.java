package de.riedelei.weather.weatherdata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


// https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}&units=metric&lang=de
@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
   private String main; // "Rain"

   private String description; // moderate Rain

   private double temp;

   private double feels_like;

   private double temp_min;

   private double temp_max;

   private int pressure;
   private int humidity;
   private int sea_level;
   private int grnd_level;
   private double windSpeed;
   private long sunrise;
   private long sunset;
   private int clouds;
   private double rain;
   private double lon;
   private double lat;
    @Id
    private Long id;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

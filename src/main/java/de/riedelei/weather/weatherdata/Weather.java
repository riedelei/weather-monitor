package de.riedelei.weather.weatherdata;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


//
@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Weather {


   @OneToMany(mappedBy="id")
   private List<WeatherCondition> weatherCondition;

   private double temp;

   private double feels_like;

   private double temp_min;

   private double temp_max;

   private String city;
   private int pressure;
   private int humidity;
   private int sea_level;
   private int grnd_level;
   private double windSpeed;
   private long sunrise;
   private long sunset;
   private int clouds;
   private double rain;
   private Double lon;
   private Double lat;
   private long visibility;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

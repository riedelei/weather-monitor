package de.riedelei.weather.weatherdata;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherCondition {
    private String description;
    private String main;
    private String icon;
    @Id
    private Long id;

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

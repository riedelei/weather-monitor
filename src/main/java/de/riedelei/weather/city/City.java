package de.riedelei.weather.city;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class City {
    @Id
    private Long Id;
    private String name;
    @Column(name = "local_name")
    private String localName;
    private String country;
    private String state;
    private double lat;
    private double lon;
}

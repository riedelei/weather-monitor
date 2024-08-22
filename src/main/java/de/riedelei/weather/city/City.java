package de.riedelei.weather.city;

import jakarta.persistence.Column;
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
public class City {
    @Id
    private Long Id;
    @Builder.Default
    private String name="";
    @Column(name = "local_name")
    private String localName;
    @Builder.Default
    private String country="";
    @Builder.Default
    private String state="";
    private double lat=0.0;
    private double lon;
}

package com.example.geo_monitor.model;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID uniqueId = UUID.randomUUID();

    private String name;

    private double latitude;

    private double longitude;

    @Enumerated(EnumType.STRING)
    private StationType type;

    private double value;
}
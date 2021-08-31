package com.eeeibra.xyzairlines.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String plate;
    private AirplaneType type;
    @OneToOne
    private Airport currentAirport;
    @ManyToMany
    private List<Airport> destinationsAirports;
    private double currentFuel;
    private double tankCapacity;

}

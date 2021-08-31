package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.models.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAll();

    Airport save(Airport airport);

    Airport update(Airport airport);

    Airport findByName(String name);

    void delete(int id);
}

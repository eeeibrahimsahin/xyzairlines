package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.models.Airplane;

import java.util.List;

public interface AirplaneService {
    List<Airplane> findAll();

    Airplane save(Airplane airplane);

    Airplane update(Airplane airplane);

    Airplane findByPlate(String plate);

    void delete(int id);

    Airplane setFlight(Airplane airplane);
}

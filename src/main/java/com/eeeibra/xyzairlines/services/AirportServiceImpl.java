package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.exceptions.AirportNotFoundException;
import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImpl implements AirportService{
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> findAll() {
        return (List<Airport>) airportRepository.findAll();
    }

    @Override
    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport findByName(String name) {
        return airportRepository.findAirportByName(name);
    }

    @Override
    public void delete(int id) {
        if (!airportRepository.existsById(id)) {
            throw new AirportNotFoundException(
                    "Airport with id " + id + " does not exist"
            );
        }
        airportRepository.deleteById(id);
    }
}

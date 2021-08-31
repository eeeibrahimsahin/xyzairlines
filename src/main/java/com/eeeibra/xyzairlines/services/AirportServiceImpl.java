package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.exceptions.AirportAlreadyExistsException;
import com.eeeibra.xyzairlines.exceptions.AirportNotFoundException;
import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.models.AirportLand;
import com.eeeibra.xyzairlines.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        if(airportRepository.existsByLand(airport.getLand())){
            throw new AirportAlreadyExistsException(
                    airport.getLand() + " exists."
            );
        }
        return airportRepository.save(airport);
    }

    @Override
    public Airport update(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public Airport findByLand(String land) {
        System.out.println("land = " + land);
        AirportLand airportLand = AirportLand.valueOf(land);
        if(airportLand == null)
            throw new AirportNotFoundException("Airport doesn't exist!");
        return airportRepository.findAirportByLand(airportLand);
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

package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.exceptions.AirplaneAlreadyExistsException;
import com.eeeibra.xyzairlines.exceptions.AirplaneHasNotEnoughFuelToArriveDestinationAirport;
import com.eeeibra.xyzairlines.exceptions.AirplaneNotFoundException;
import com.eeeibra.xyzairlines.models.Airplane;
import com.eeeibra.xyzairlines.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> findAll() {
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @Override
    public Airplane save(Airplane airplane) {
        if (airplaneRepository.existsAirplaneByPlate(airplane.getPlate()))
            throw new AirplaneAlreadyExistsException("Airplane with plate " + airplane.getId() + " already exists!");
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane update(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane findByPlate(String plate) {
        return airplaneRepository.findAirplaneByPlate(plate);
    }

    @Override
    public void delete(int id) {
        if (!airplaneRepository.existsById(id)) {
            throw new AirplaneNotFoundException("Airplane with id " + id + " does not exist");
        }
        airplaneRepository.deleteById(id);
    }

    @Override
    public Airplane setFlight(Airplane airplane) {
        if (airplane.getCurrentFuel() < 2000)
            throw new AirplaneHasNotEnoughFuelToArriveDestinationAirport("Fuel nor enough!");
        Optional<Airplane> airplaneOptional = airplaneRepository.findById(airplane.getId());
        airplaneOptional.get().setDestinationsAirports(airplane.getDestinationsAirports());
        return airplaneRepository.save(airplaneOptional.get());
    }
}

package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.exceptions.AirplaneNotFoundException;
import com.eeeibra.xyzairlines.models.Airplane;
import com.eeeibra.xyzairlines.repositories.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AirplaneServiceImpl implements AirplaneService{
    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> findAll() {
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane update(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public void delete(int id) {
        if (!airplaneRepository.existsById(id)) {
            throw new AirplaneNotFoundException("Airplane with id " + id + " does not exist");
        }
        airplaneRepository.deleteById(id);
    }
}

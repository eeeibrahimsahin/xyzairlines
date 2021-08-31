package com.eeeibra.xyzairlines.repositories;

import com.eeeibra.xyzairlines.models.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane,Integer> {
    boolean existsAirplaneByPlate(String plate);
    Airplane findAirplaneByPlate(String plate);
}

package com.eeeibra.xyzairlines.repositories;

import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.models.AirportLand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Integer> {
    Airport findAirportByName(String name);

    boolean existsByLand(AirportLand land);

    boolean existsByName(String name);
}

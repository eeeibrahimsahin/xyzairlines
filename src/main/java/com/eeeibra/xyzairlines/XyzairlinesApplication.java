package com.eeeibra.xyzairlines;

import com.eeeibra.xyzairlines.models.Airplane;
import com.eeeibra.xyzairlines.models.AirplaneType;
import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.models.AirportLand;
import com.eeeibra.xyzairlines.services.AirplaneService;
import com.eeeibra.xyzairlines.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@SpringBootApplication
public class XyzairlinesApplication implements CommandLineRunner {
    @Autowired
    private AirportService airportService;
    @Autowired
    private AirplaneService airplaneService;

    public static void main(String[] args) {
        SpringApplication.run(XyzairlinesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Airport airport = new Airport(1, "Schiphol", AirportLand.AMSTERDAM, null);
        Airport airport1 = new Airport(2, "London City", AirportLand.LONDON, null);
        Airport airport2 = new Airport(3, "Berlin City", AirportLand.BERLIN, null);
        Airport airport3 = new Airport(4, "Stockholm City", AirportLand.STOCKHOLM, null);
        Airport airport4 = new Airport(5, "Paris City", AirportLand.PARIS, null);
        airportService.save(airport);
        airportService.save(airport1);
        airportService.save(airport2);
        airportService.save(airport3);
        airportService.save(airport4);
    }


}

package com.eeeibra.xyzairlines.controllers;

import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.findAll());
    }

    @PostMapping
    public ResponseEntity<Airport> save(@RequestBody Airport airport) {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.save(airport));
    }

    @PutMapping
    public ResponseEntity<Airport> update(@RequestBody Airport airport) {
        return ResponseEntity.status(HttpStatus.OK).body(airportService.update(airport));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        airportService.delete(id);
    }

    @GetMapping("/{name}")
    public Airport findByName(@PathVariable String name){
        return airportService.findByName(name);
    }
}

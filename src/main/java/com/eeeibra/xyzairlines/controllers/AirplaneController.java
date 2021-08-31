package com.eeeibra.xyzairlines.controllers;

import com.eeeibra.xyzairlines.models.Airplane;
import com.eeeibra.xyzairlines.services.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplane")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

    @GetMapping("/plate/{plate}")
    public ResponseEntity<Airplane> findByPlate(@PathVariable String plate) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.findByPlate(plate));
    }

    @GetMapping
    public ResponseEntity<List<Airplane>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.findAll());
    }

    @PostMapping
    public ResponseEntity<Airplane> save(@RequestBody Airplane airplane) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.save(airplane));
    }

    @PostMapping("/setflight")
    public ResponseEntity<Airplane> setFlight(@RequestBody Airplane airplane) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.setFlight(airplane));
    }

    @PutMapping
    public ResponseEntity<Airplane> update(@RequestBody Airplane airplane) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.update(airplane));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        airplaneService.delete(id);
    }

}

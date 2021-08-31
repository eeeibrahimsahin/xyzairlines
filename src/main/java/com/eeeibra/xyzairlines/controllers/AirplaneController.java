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

    @GetMapping
    public ResponseEntity<List<Airplane>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.findAll());
    }

    @PostMapping
    public ResponseEntity<Airplane> save(@RequestBody Airplane airplane) {
        return ResponseEntity.status(HttpStatus.OK).body(airplaneService.save(airplane));
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

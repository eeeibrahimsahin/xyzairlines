package com.eeeibra.xyzairlines.controllers;

import com.eeeibra.xyzairlines.models.AirplaneType;
import com.eeeibra.xyzairlines.models.AirportLand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/constants")
public class ConstantsController {
    @GetMapping
    public Map<String, List<String>> getAllConstants() {
        List<String> airplaneTypes = new ArrayList<>();
        List<String> airportLands = new ArrayList<>();
        Arrays.stream(AirplaneType.values()).map(airplaneType -> airplaneType.toString()).forEach(airplaneTypes::add);
        Arrays.stream(AirportLand.values()).map(airportLand -> airportLand.toString()).forEach(airportLands::add);
        return Map.of("airplaneTypes", airplaneTypes, "airportLands", airportLands);
    }
}

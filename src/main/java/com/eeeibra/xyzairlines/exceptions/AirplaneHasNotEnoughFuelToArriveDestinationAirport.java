package com.eeeibra.xyzairlines.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AirplaneHasNotEnoughFuelToArriveDestinationAirport extends RuntimeException {
    public AirplaneHasNotEnoughFuelToArriveDestinationAirport(String msg) {
        super(msg);
    }
}

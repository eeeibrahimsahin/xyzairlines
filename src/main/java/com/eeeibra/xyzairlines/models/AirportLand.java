package com.eeeibra.xyzairlines.models;

public enum AirportLand {
    LONDON("LONDON"),
    AMSTERDAM("AMSTERDAM"),
    PARIS("PARIS"),
    STOCKHOLM("STOCKHOLM"),
    BERLIN("BERLIN");

    private String value;

    AirportLand(String value) {
        this.value = value;
    }
}

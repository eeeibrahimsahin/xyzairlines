package com.eeeibra.xyzairlines.models;

public enum AirplaneType {
    AIRBUS_A300("AIRBUS_A300"),
    AIRBUS_A310("AIRBUS_A310"),
    AIRBUS_A320("AIRBUS_A320"),
    BOEING_737("BOEING_737"),
    BOEING_747("BOEING_747"),
    BOEING_767("BOEING_767");

    private String value;

    AirplaneType(String value) {
        this.value = value;
    }
}

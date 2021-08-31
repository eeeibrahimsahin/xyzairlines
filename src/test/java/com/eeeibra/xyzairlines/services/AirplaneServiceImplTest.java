package com.eeeibra.xyzairlines.services;

import com.eeeibra.xyzairlines.exceptions.AirplaneNotFoundException;
import com.eeeibra.xyzairlines.models.Airplane;
import com.eeeibra.xyzairlines.models.AirplaneType;
import com.eeeibra.xyzairlines.models.Airport;
import com.eeeibra.xyzairlines.models.AirportLand;
import com.eeeibra.xyzairlines.repositories.AirplaneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AirplaneServiceImplTest {
    @Mock
    private AirplaneRepository airplaneRepository;
    @InjectMocks
    private AirplaneServiceImpl underTest;
    private Airport airport;
    private Airplane airplane;

    @BeforeEach
    void setUp() {
        airport = new Airport(1, "Schiphol", AirportLand.AMSTERDAM, null);
        airplane = new Airplane(1, "TK-737", AirplaneType.AIRBUS_A300, airport, null, 1000, 5000);
    }

    @Test
    void canFindAll() {
        //Arrange
        //Act
        underTest.findAll();
        //Assert
        verify(airplaneRepository).findAll();
    }

    @Test
    void canSave() {
        //Arrange
        Mockito.when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);
        //Act
        Airplane savedAirplane = underTest.save(airplane);
        //Arrange
        assertThat(savedAirplane).isEqualTo(airplane);
    }

    @Test
    void canUpdate() {
        //Arrange
        Mockito.when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);
        //Act
        Airplane savedAirplane = underTest.update(airplane);
        //Arrange
        assertThat(savedAirplane).isEqualTo(airplane);
    }

    @Test
    void canDelete() {
        //Arrange
        int id = 2;
        given(airplaneRepository.existsById(id)).willReturn(true);
        //Act
        underTest.delete(id);
        //Assert
        verify(airplaneRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteAirplaneNotFound(){
        //Arrange
        int id = 2;
        given(airplaneRepository.existsById(id)).willReturn(false);
        //Act
        underTest.delete(id);
        //Assert
        assertThatThrownBy(() -> underTest.delete(id))
                .isInstanceOf(AirplaneNotFoundException.class);
               // .hasMessageContaining("Airplane with id " + id + " does not exist");
    }
}
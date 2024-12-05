package ru.ramazanmamyrbek.sensorapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.SensorResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.SensorService;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SensorControllerTest {

    @InjectMocks
    private SensorController sensorController;

    @Mock
    private SensorService sensorService;

    @Test
    void shouldCreateSensorSuccessfully() {
        CreateSensorRequestDto requestDto = new CreateSensorRequestDto("Sensor1");

        ResponseEntity<Map<String, String>> response = sensorController.createSensor(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created", response.getBody().get("message"));
    }

    @Test
    void shouldGetAllSensors() {
        List<SensorResponseDto> sensors = List.of(
                new SensorResponseDto(1L, "Sensor1"),
                new SensorResponseDto(2L, "Sensor2")
        );

        when(sensorService.getAllSensors()).thenReturn(sensors);

        ResponseEntity<List<SensorResponseDto>> response = sensorController.getAllSensors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sensors, response.getBody());
    }
}


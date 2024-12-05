package ru.ramazanmamyrbek.sensorapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateMeasurementRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.MeasurementResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RainyDaysResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.MeasurementService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeasurementControllerTest {

    @InjectMocks
    private MeasurementController measurementController;

    @Mock
    private MeasurementService measurementService;

    @Test
    void shouldCreateMeasurementSuccessfully() {
        CreateMeasurementRequestDto requestDto = new CreateMeasurementRequestDto(10.0, true, new CreateSensorRequestDto("Sensor1"));


        ResponseEntity<Map<String, String>> response = measurementController.createMeasurement(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Created", response.getBody().get("message"));
    }

    @Test
    void shouldGetAllMeasurements() {
        List<MeasurementResponseDto> measurements = List.of(
                new MeasurementResponseDto(10.0, true, new CreateSensorRequestDto("Sensor1"), LocalDateTime.now())
        );

        when(measurementService.getAllMeasurements()).thenReturn(measurements);

        ResponseEntity<List<MeasurementResponseDto>> response = measurementController.getAllMeasurements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(measurements, response.getBody());
    }

    @Test
    void shouldGetRainyDaysCount() {
        RainyDaysResponseDto responseDto = new RainyDaysResponseDto(5L);

        when(measurementService.getRainyDaysCount()).thenReturn(responseDto);

        ResponseEntity<RainyDaysResponseDto> response = measurementController.getRainyDaysCount();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }
}


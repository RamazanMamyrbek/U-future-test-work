package ru.ramazanmamyrbek.sensorapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.SensorResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.SensorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
@Tag(name = "Sensors Controller", description = "Endpoints for Sensor management")
@SecurityRequirement(name = "JWT")
public class SensorController {
    private final SensorService sensorService;

    @PostMapping("/registration")
    @Operation(summary = "Create sensor", description = "Endpoint for creating sensor")
    public ResponseEntity<Map<String, String>> createSensor(@RequestBody @Valid CreateSensorRequestDto createSensorRequestDto) {
        sensorService.createSensor(createSensorRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message","Created"
        ));
    }
    @GetMapping
    @Operation(summary = "Get all sensors", description = "Getting all sensors from DB")
    public ResponseEntity<List<SensorResponseDto>> getAllSensors() {
        List<SensorResponseDto> sensors = sensorService.getAllSensors();
        return ResponseEntity.ok().body(sensors);
    }
}

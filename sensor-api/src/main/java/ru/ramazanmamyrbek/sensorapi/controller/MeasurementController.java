package ru.ramazanmamyrbek.sensorapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateMeasurementRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.MeasurementResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RainyDaysResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.MeasurementService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/measurements")
@RequiredArgsConstructor
@Tag(name = "Measurment Controller", description = "Endpoint for Measurements management")
@SecurityRequirement(name = "JWT")
public class MeasurementController {
    private final MeasurementService measurementService;

    @PostMapping("/add")
    @Operation(summary = "Add measurement", description = "Endpoint for adding measurement")
    public ResponseEntity<Map<String, String>> createMeasurement(@RequestBody @Valid CreateMeasurementRequestDto requestDto) {
        measurementService.createMeasurement(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message","Created"
        ));
    }

    @GetMapping
    @Operation(summary = "Get all measurement", description = "Endpoint for getting all measurements")
    public ResponseEntity<List<MeasurementResponseDto>> getAllMeasurements() {
        List<MeasurementResponseDto> measurements = measurementService.getAllMeasurements();
        return ResponseEntity.ok().body(measurements);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<RainyDaysResponseDto> getRainyDaysCount() {
        RainyDaysResponseDto responseDto = measurementService.getRainyDaysCount();
        return ResponseEntity.ok().body(responseDto);
    }
}

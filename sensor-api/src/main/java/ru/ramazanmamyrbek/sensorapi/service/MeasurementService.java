package ru.ramazanmamyrbek.sensorapi.service;

import ru.ramazanmamyrbek.sensorapi.dto.request.CreateMeasurementRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.MeasurementResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RainyDaysResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.Measurement;

import java.util.List;

public interface MeasurementService {
    Measurement createMeasurement(CreateMeasurementRequestDto createMeasurementRequestDto);

    List<MeasurementResponseDto> getAllMeasurements();

    RainyDaysResponseDto getRainyDaysCount();
}

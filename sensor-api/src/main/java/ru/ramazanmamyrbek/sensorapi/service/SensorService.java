package ru.ramazanmamyrbek.sensorapi.service;

import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.SensorResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.Sensor;

import java.util.List;

public interface SensorService {
    Sensor createSensor(CreateSensorRequestDto createSensorRequestDto);

    List<SensorResponseDto> getAllSensors();

    boolean checkSensorDoesExists(String name);

    Sensor getSensorByName(String name);
}

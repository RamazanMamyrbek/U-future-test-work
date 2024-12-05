package ru.ramazanmamyrbek.sensorapi.mapper;

import lombok.experimental.UtilityClass;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.entity.Sensor;

@UtilityClass
public class SensorMapper {
    public Sensor createSensorRequestDtoToSensor(CreateSensorRequestDto requestDto, Sensor sensor) {
        sensor.setName(requestDto.name());
        return sensor;
    }
}

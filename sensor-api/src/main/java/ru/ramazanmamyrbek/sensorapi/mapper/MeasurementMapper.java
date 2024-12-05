package ru.ramazanmamyrbek.sensorapi.mapper;

import lombok.experimental.UtilityClass;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateMeasurementRequestDto;
import ru.ramazanmamyrbek.sensorapi.entity.Measurement;


@UtilityClass
public class MeasurementMapper {
    public Measurement createMeasurementRequestDtoToMeasurement(CreateMeasurementRequestDto requestDto, Measurement measurement) {
        measurement.setValue(requestDto.value());
        measurement.setRaining(requestDto.raining());
        return measurement;
    }
}

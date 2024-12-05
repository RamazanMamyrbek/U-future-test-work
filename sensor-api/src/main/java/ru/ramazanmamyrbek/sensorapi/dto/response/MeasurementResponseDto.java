package ru.ramazanmamyrbek.sensorapi.dto.response;

import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;

import java.time.LocalDateTime;

public record MeasurementResponseDto(
        Double value,
        Boolean raining,
        CreateSensorRequestDto sensor,
        LocalDateTime time) {
}

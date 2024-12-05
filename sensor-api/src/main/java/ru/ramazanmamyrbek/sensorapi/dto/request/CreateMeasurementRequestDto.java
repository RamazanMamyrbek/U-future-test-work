package ru.ramazanmamyrbek.sensorapi.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record CreateMeasurementRequestDto(
        @Min(value = -100, message = "Measurement value should be greater than -100")
        @Max(value = 100, message = "Measurement value should be less than 100")
        @NotNull(message = "Measurement value should not be null")
        Double value,
        @NotNull(message = "Raining should not be null")
        Boolean raining,
        @NotNull(message = "Sensor should not be null")
        CreateSensorRequestDto sensor
) {
}

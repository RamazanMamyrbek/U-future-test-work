package ru.ramazanmamyrbek.sensorapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSensorRequestDto(
        @NotEmpty(message = "Sensor Name should not be empty")
        @NotNull(message = "Sensor Name should not be null")
        String name
) {
}

package ru.ramazanmamyrbek.sensorapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotEmpty(message = "Username should not be empty")
        @NotNull(message = "Username should not be null")
        String username,
        @NotEmpty(message = "Password should not be empty")
        @NotNull(message = "Password should not be null")
        String password
) {
}

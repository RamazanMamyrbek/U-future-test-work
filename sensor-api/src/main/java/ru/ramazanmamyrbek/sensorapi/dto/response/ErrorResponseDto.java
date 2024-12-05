package ru.ramazanmamyrbek.sensorapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(description = "Error response template for errors")
public record ErrorResponseDto(
        String apiPath,
        HttpStatus errorCode,
        String errorMessage,
        LocalDateTime errorTime
) {
}

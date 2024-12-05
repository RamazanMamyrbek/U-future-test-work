package ru.ramazanmamyrbek.sensorapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ramazanmamyrbek.sensorapi.dto.request.LoginRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.RegisterRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.LoginResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RegisterResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Endpoints for login/registration")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Registration", description = "Endpoint for registration")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody @Valid RegisterRequestDto requestDto) {
        RegisterResponseDto responseDto = userService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Endpoint for login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        LoginResponseDto responseDto = userService.login(loginRequestDto);
        return ResponseEntity.ok().body(responseDto);
    }
}

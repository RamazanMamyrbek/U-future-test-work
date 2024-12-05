package ru.ramazanmamyrbek.sensorapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ramazanmamyrbek.sensorapi.dto.request.LoginRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.RegisterRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.LoginResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RegisterResponseDto;
import ru.ramazanmamyrbek.sensorapi.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private UserService userService;

    @Test
    void shouldRegisterUserSuccessfully() {
        RegisterRequestDto requestDto = new RegisterRequestDto("username", "password");
        RegisterResponseDto responseDto = new RegisterResponseDto("username", "jwt-token");

        when(userService.registerUser(requestDto)).thenReturn(responseDto);

        ResponseEntity<RegisterResponseDto> response = authController.registerUser(requestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }

    @Test
    void shouldLoginUserSuccessfully() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("username", "password");
        LoginResponseDto responseDto = new LoginResponseDto("jwt-token");

        when(userService.login(loginRequestDto)).thenReturn(responseDto);

        ResponseEntity<LoginResponseDto> response = authController.login(loginRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
    }
}


package ru.ramazanmamyrbek.sensorapi.service;

import ru.ramazanmamyrbek.sensorapi.dto.request.LoginRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.RegisterRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.LoginResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RegisterResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.User;

public interface UserService {
    RegisterResponseDto registerUser(RegisterRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    User getUserByUsername(String username);
}

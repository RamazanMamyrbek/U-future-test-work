package ru.ramazanmamyrbek.sensorapi.mapper;

import lombok.experimental.UtilityClass;
import ru.ramazanmamyrbek.sensorapi.dto.request.RegisterRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RegisterResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.User;

@UtilityClass
public class UserMapper {
    public User registerRequestToUser(RegisterRequestDto requestDto, User user) {
        user.setUsername(requestDto.username());
        user.setPassword(requestDto.password());
        return user;
    }
}

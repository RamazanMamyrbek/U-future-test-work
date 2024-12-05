package ru.ramazanmamyrbek.sensorapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ramazanmamyrbek.sensorapi.config.security.JwtService;
import ru.ramazanmamyrbek.sensorapi.dto.request.LoginRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.RegisterRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.LoginResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RegisterResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.User;
import ru.ramazanmamyrbek.sensorapi.exception.InvalidPasswordException;
import ru.ramazanmamyrbek.sensorapi.exception.UserAlreadyRegisteredException;
import ru.ramazanmamyrbek.sensorapi.mapper.UserMapper;
import ru.ramazanmamyrbek.sensorapi.repository.UserRepository;
import ru.ramazanmamyrbek.sensorapi.service.UserService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    @Transactional
    public RegisterResponseDto registerUser(RegisterRequestDto requestDto) {
        if(userRepository.findUserByUsername(requestDto.username()).isPresent()) {
            throw new UserAlreadyRegisteredException("User with username %s is already registered".formatted(requestDto.username()));
        }
        User user = UserMapper.registerRequestToUser(requestDto, User.builder().build());
        user.setPassword(passwordEncoder.encode(requestDto.password()));
        String jwt = jwtService.generateAccessToken(user.getUsername());
        userRepository.save(user);
        return new RegisterResponseDto(user.getUsername(), jwt);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = getUserByUsername(loginRequestDto.username());
        if(!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }
        String jwt = jwtService.generateAccessToken(user.getUsername());
        return new LoginResponseDto(jwt);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username %s is not found".formatted(username)));
    }
}

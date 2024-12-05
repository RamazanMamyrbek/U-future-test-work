package ru.ramazanmamyrbek.sensorapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.SensorResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.Sensor;
import ru.ramazanmamyrbek.sensorapi.exception.ResourceNotFoundException;
import ru.ramazanmamyrbek.sensorapi.exception.SensorAlreadyExistsException;
import ru.ramazanmamyrbek.sensorapi.mapper.SensorMapper;
import ru.ramazanmamyrbek.sensorapi.repository.SensorRepository;
import ru.ramazanmamyrbek.sensorapi.service.SensorService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;
    @Override
    @Transactional
    public Sensor createSensor(CreateSensorRequestDto createSensorRequestDto) {
        if(sensorRepository.findSensorByName(createSensorRequestDto.name()).isPresent()) {
            throw new SensorAlreadyExistsException("Sensor with name %s does already exists".formatted(createSensorRequestDto.name()));
        }
        Sensor sensor = SensorMapper.createSensorRequestDtoToSensor(createSensorRequestDto, Sensor.builder().build());
        return sensorRepository.save(sensor);
    }

    @Override
    public List<SensorResponseDto> getAllSensors() {
        return sensorRepository
                .findAll()
                .stream()
                .map(sensor -> new SensorResponseDto(sensor.getId(), sensor.getName()))
                .toList();
    }

    @Override
    public boolean checkSensorDoesExists(String name) {
        return sensorRepository.findSensorByName(name).isPresent();
    }

    @Override
    public Sensor getSensorByName(String name) {
        return sensorRepository.findSensorByName(name).orElseThrow(() -> new ResourceNotFoundException("Sensor with name %s is not found".formatted(name)));
    }
}

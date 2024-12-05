package ru.ramazanmamyrbek.sensorapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateMeasurementRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.request.CreateSensorRequestDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.MeasurementResponseDto;
import ru.ramazanmamyrbek.sensorapi.dto.response.RainyDaysResponseDto;
import ru.ramazanmamyrbek.sensorapi.entity.Measurement;
import ru.ramazanmamyrbek.sensorapi.mapper.MeasurementMapper;
import ru.ramazanmamyrbek.sensorapi.repository.MeasurementRepository;
import ru.ramazanmamyrbek.sensorapi.service.MeasurementService;
import ru.ramazanmamyrbek.sensorapi.service.SensorService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Override
    @Transactional
    public Measurement createMeasurement(CreateMeasurementRequestDto createMeasurementRequestDto) {
        Measurement measurement = MeasurementMapper.createMeasurementRequestDtoToMeasurement(createMeasurementRequestDto, Measurement.builder().build());
        measurement.setSensor(sensorService.getSensorByName(createMeasurementRequestDto.sensor().name()));
        measurement.setTime(LocalDateTime.now());
        return measurementRepository.save(measurement);
    }

    @Override
    public List<MeasurementResponseDto> getAllMeasurements() {
        return measurementRepository.findAll()
                .stream()
                .map(measurement -> new MeasurementResponseDto(
                        measurement.getValue(),
                        measurement.getRaining(),
                        new CreateSensorRequestDto(measurement.getSensor().getName()),
                        measurement.getTime()
                ))
                .toList();
    }

    @Override
    public RainyDaysResponseDto getRainyDaysCount() {
        Long count = measurementRepository.findAll()
                .stream()
                .filter(Measurement::getRaining)
                .count();
        return new RainyDaysResponseDto(count);
    }
}

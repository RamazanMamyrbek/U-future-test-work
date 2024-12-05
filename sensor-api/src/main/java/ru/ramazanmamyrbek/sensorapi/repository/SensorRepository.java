package ru.ramazanmamyrbek.sensorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ramazanmamyrbek.sensorapi.entity.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findSensorByName(String name);
}

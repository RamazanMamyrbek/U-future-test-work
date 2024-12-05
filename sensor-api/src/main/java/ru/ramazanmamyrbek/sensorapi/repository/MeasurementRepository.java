package ru.ramazanmamyrbek.sensorapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ramazanmamyrbek.sensorapi.entity.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

}

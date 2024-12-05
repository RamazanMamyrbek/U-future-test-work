package ru.ramazanmamyrbek.sensorclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDto {
    private Double value;
    private Boolean raining;
    private SensorDto sensor;
}


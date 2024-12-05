package ru.ramazanmamyrbek.sensorapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"sensor"})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double value;
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
    private LocalDateTime time;
}

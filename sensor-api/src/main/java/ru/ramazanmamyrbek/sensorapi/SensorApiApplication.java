package ru.ramazanmamyrbek.sensorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SensorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorApiApplication.class, args);
    }

}

package ru.ramazanmamyrbek.sensorapi.exception;

public class SensorAlreadyExistsException extends RuntimeException{
    public SensorAlreadyExistsException(String message) {
        super(message);
    }
}

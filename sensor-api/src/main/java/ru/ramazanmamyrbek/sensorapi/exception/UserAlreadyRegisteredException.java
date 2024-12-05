package ru.ramazanmamyrbek.sensorapi.exception;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}

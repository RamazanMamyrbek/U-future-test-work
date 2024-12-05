package ru.ramazanmamyrbek.sensorapi.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.ramazanmamyrbek.sensorapi.dto.response.ErrorResponseDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SensorAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleSensorAlreadyExistsException(SensorAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponseDto);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidPasswordException(InvalidPasswordException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
}

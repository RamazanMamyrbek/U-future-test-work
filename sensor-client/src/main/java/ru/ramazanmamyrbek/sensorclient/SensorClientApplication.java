package ru.ramazanmamyrbek.sensorclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.ramazanmamyrbek.sensorclient.dto.MeasurementDto;
import ru.ramazanmamyrbek.sensorclient.dto.RegisterUserDto;
import ru.ramazanmamyrbek.sensorclient.dto.SensorDto;

import java.util.*;

@SpringBootApplication
public class SensorClientApplication {
    private static String JWT = "";
    private static final String USERNAME = "sensor-client";
    private static final String PASSWORD = "12345";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        //Register user
        registerUser(restTemplate);
        //Login
        loginUser(restTemplate);

        //Register sensor(If you try to execute this code again when you already registered a sensor,there will be validation error
        SensorDto sensorDto = new SensorDto("ClientSensor");
        registerSensor(sensorDto,restTemplate);

        //Send 1000 measurements
        send1000Measurements(sensorDto, restTemplate);

        //Get measurements
        getAllMeasurements(restTemplate);


    }

    private static void loginUser(RestTemplate restTemplate) {
        try {
            System.out.println("Login");
            String url = "http://localhost:8080/auth/login";
            RegisterUserDto registerUserDto = new RegisterUserDto(USERNAME, PASSWORD);
            String response = restTemplate.postForObject(url, registerUserDto, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            // Извлекаем значение jwt
            JWT = jsonNode.get("jwt").asText();
            System.out.println("-----------------------");
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("-----------------------");
        }
    }

    private static void registerUser(RestTemplate restTemplate) {
        try {
            System.out.println("-----------------------");
            System.out.println("Registration");
            String url = "http://localhost:8080/auth/register";
            RegisterUserDto registerUserDto = new RegisterUserDto(USERNAME, PASSWORD);
            String response = restTemplate.postForObject(url, registerUserDto, String.class);
            JsonNode jsonNode = objectMapper.readTree(response);
            // Извлекаем значение jwt
            JWT = jsonNode.get("jwt").asText();
            System.out.println("-----------------------");
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("-----------------------");
        }
    }


    public static void registerSensor(SensorDto sensorDto, RestTemplate restTemplate) {
        try {
            String url = "http://localhost:8080/sensors/registration";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + JWT);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<SensorDto> request = new HttpEntity<>(sensorDto, headers);
            String response = restTemplate.postForObject(url, request, String.class);

            System.out.println("Sensor registered successfully");
            System.out.println("-----------------------");
        } catch (Exception exception) {
            System.out.println("Error while trying to create a sensor");
            System.out.println(exception.getMessage());
            System.out.println("-----------------------");
        }
    }


    private static void send1000Measurements(SensorDto sensorDto, RestTemplate restTemplate) {
        try {
            Random rnd = new Random();
            String urlAddMeasurement = "http://localhost:8080/measurements/add";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + JWT);
            headers.setContentType(MediaType.APPLICATION_JSON);

            for (int i = 0; i < 1000; i++) {
                MeasurementDto measurementDto = new MeasurementDto(rnd.nextDouble() * 45, rnd.nextBoolean(), sensorDto);
                HttpEntity<MeasurementDto> request = new HttpEntity<>(measurementDto, headers);
                restTemplate.postForObject(urlAddMeasurement, request, String.class);
            }
            System.out.println("Created 1000 measurements for sensor %s".formatted(sensorDto.getName()));
            System.out.println("-----------------------");
        } catch (Exception ex) {
            System.out.println("Error while trying to send 1000 measurements");
            System.out.println(ex.getMessage());
            System.out.println("-----------------------");
        }
    }

    public static void getAllMeasurements(RestTemplate restTemplate) {
        try {
            String getAllMeasurementsUrl = "http://localhost:8080/measurements";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + JWT);

            HttpEntity<Void> request = new HttpEntity<>(headers);
            ResponseEntity<MeasurementDto[]> response = restTemplate.exchange(
                    getAllMeasurementsUrl, HttpMethod.GET, request, MeasurementDto[].class);

            MeasurementDto[] measurements = response.getBody();
            System.out.println("Get all measurements: ");
            System.out.println(Arrays.toString(measurements));
            System.out.println("-----------------------");
        } catch (Exception ex) {
            System.out.println("Error while trying to get all measurements");
            System.out.println(ex.getMessage());
            System.out.println("-----------------------");
        }
    }


}

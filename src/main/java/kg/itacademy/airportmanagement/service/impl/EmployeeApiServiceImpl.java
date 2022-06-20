package kg.itacademy.airportmanagement.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kg.itacademy.airportmanagement.model.EmployeeApiListResponse;
import kg.itacademy.airportmanagement.model.EmployeeApiModel;
import kg.itacademy.airportmanagement.model.EmployeeApiResponse;
import kg.itacademy.airportmanagement.service.EmployeeApiService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeApiServiceImpl implements EmployeeApiService {
    private static final String HOST = "https://dummy.restapiexample.com";
    private static final String GET_ALL = HOST + "/api/v1/employees";
    private static final String GET_BY_ID = HOST + "/api/v1/employee/";

    private static final String CREATE = HOST + "/api/v1/create";

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public EmployeeApiListResponse getAll() {
        return getAllForEntity();
    }

    @Override
    public EmployeeApiResponse getById(Long id) {
       return getByIdForEntityWithParsing(id);
//       return getByIdForEntityWithoutParsing(id);
//        return getByIdForObject(id);
    }

    @Override
    public EmployeeApiResponse create(EmployeeApiModel body) {
        return createWithoutHeaders(body);
    }

    private EmployeeApiResponse getByIdForEntityWithParsing(Long id) {
        //Формируем ссылку
        final String getByIdFullPath = GET_BY_ID + id.toString();

        ResponseEntity<String> response = restTemplate.getForEntity(getByIdFullPath, String.class);

        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        EmployeeApiResponse result = null;
        try {
            result = mapper.readValue(response.getBody(), EmployeeApiResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private EmployeeApiResponse getByIdForEntityWithoutParsing(Long id) {
        //Формируем ссылку
        final String getByIdFullPath = GET_BY_ID + id.toString();
        ResponseEntity<EmployeeApiResponse> response = restTemplate.getForEntity(getByIdFullPath, EmployeeApiResponse.class);
        System.out.println(response);
        EmployeeApiResponse result = response.getBody();
        return result;
    }

    private EmployeeApiResponse getByIdForObject(Long id) {
        //Формируем ссылку
        final String getByIdFullPath = GET_BY_ID + id.toString();
        EmployeeApiResponse response = restTemplate.getForObject(getByIdFullPath, EmployeeApiResponse.class);
        System.out.println(response);
        EmployeeApiResponse result = response;
        return result;
    }

    private EmployeeApiListResponse getAllForEntity() {
        //Формируем ссылку
        final String getByIdFullPath = GET_ALL;
        ResponseEntity<EmployeeApiListResponse> response = restTemplate.getForEntity(getByIdFullPath, EmployeeApiListResponse.class);
        System.out.println(response);
        EmployeeApiListResponse result = response.getBody();
        return result;
    }

    private EmployeeApiResponse createWithoutHeaders(EmployeeApiModel body) {
//                RequestEntity<EmployeeApiModel> request = new RequestEntity<>(body);
        HttpEntity<EmployeeApiModel> request = new HttpEntity<>(body);
        ResponseEntity<EmployeeApiResponse> result = restTemplate.postForEntity(CREATE, request, EmployeeApiResponse.class);
        return result.getBody();
    }

    private EmployeeApiResponse createWithHeaders(EmployeeApiModel body) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT_CHARSET, "asdfasdf");
        headers.add("Custom", "asdfasdf");
        HttpEntity<EmployeeApiModel> request = new HttpEntity<>(body, headers);
        ResponseEntity<EmployeeApiResponse> result = restTemplate.postForEntity(CREATE, request, EmployeeApiResponse.class);
        return result.getBody();
    }
}

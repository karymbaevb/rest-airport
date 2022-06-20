package kg.itacademy.airportmanagement.service;

import kg.itacademy.airportmanagement.model.EmployeeApiListResponse;
import kg.itacademy.airportmanagement.model.EmployeeApiModel;
import kg.itacademy.airportmanagement.model.EmployeeApiResponse;

import java.util.List;

public interface EmployeeApiService {
    EmployeeApiListResponse getAll();

    EmployeeApiResponse getById(Long id);

    EmployeeApiResponse create(EmployeeApiModel body);
}

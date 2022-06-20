package kg.itacademy.airportmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeApiListResponse {
    private List<EmployeeApiModel> data;
    private String status;
    private String message;
}

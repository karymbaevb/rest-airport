package kg.itacademy.airportmanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeApiResponse {
    private EmployeeApiModel data;
    private String status;
    private String message;
}

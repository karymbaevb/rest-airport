package kg.itacademy.airportmanagement.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeGenericApiResponse<T> {
    private T data;
    private String status;
    private String message;
}

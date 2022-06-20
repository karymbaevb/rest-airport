package kg.itacademy.airportmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeApiModel {
    private Long id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private Double salary;

    @JsonProperty("employee_age")
    private Integer age;

    @JsonProperty("profile_image")
    private String profileImage;
}

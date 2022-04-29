package kg.itacademy.airportmanagement.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorModel {
    private String msg;
    private String exceptionClassName;
}

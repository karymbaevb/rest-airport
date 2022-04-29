package kg.itacademy.airportmanagement.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ImDontKnowException extends RuntimeException {
    private HttpStatus httpStatus ;


    public ImDontKnowException(String s) {
        super(s);
    }

    public HttpStatus getStatus(){
        return HttpStatus.I_AM_A_TEAPOT;
    }
}

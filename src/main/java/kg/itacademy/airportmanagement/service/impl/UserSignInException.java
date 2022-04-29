package kg.itacademy.airportmanagement.service.impl;

import org.springframework.http.HttpStatus;

public class UserSignInException extends RuntimeException {
    public UserSignInException(String s, HttpStatus notFound) {
    }
}

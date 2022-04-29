package kg.itacademy.airportmanagement.service;

import kg.itacademy.airportmanagement.model.UserAuthModel;
import kg.itacademy.airportmanagement.model.UserModel;

public interface UserService {
    String getToken(UserAuthModel userAuthDto);
    String createUser(UserModel userAuthDto);
}

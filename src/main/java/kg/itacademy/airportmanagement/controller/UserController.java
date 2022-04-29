package kg.itacademy.airportmanagement.controller;


import kg.itacademy.airportmanagement.model.UserAuthModel;
import kg.itacademy.airportmanagement.model.UserModel;
import kg.itacademy.airportmanagement.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    final UserService userService;

    @PostMapping("/sign-in")
    public String getAuthToken(@RequestBody UserAuthModel userAuthDto) {
        return userService.getToken(userAuthDto);
    }

    @PostMapping("/sign-up")
    public String register(@RequestBody UserModel userModel) {
        return userService.createUser(userModel);
    }
}
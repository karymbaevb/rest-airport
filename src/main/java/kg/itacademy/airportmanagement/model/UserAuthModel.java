package kg.itacademy.airportmanagement.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthModel {

    @NotBlank(message = "Login can't be blank")
    String login;

    @NotBlank(message = "Password can't be blank")
    String password;
}

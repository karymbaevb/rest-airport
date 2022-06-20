package kg.itacademy.airportmanagement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "myapplication")
@Getter
@Setter
public class MyApplication {
    Airport airport;
}

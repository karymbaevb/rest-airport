package kg.itacademy.airportmanagement.configuration;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.login, t.password, t.is_active FROM user_airport t WHERE t.login = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.login, r.name_role " +
                                "FROM users_roles ur " +
                                "INNER JOIN users u " +
                                "   on ur.user_id = u.id " +
                                "INNER JOIN roles r " +
                                "   on ur.role_id = r.id " +
                                "WHERE u.login = ? AND u.is_active = 1"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/api/aircraft/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/aircraft/*").hasRole("Admin")
                .antMatchers(HttpMethod.PUT, "/api/aircraft/*").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/api/aircraft/*").hasRole("Admin")

                .antMatchers(HttpMethod.GET, "/api/airport/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/airport/*").hasAnyRole("Admin", "Moderator", "User")
                .antMatchers(HttpMethod.PUT, "/api/airport/*").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/api/airport/*").hasRole("Admin")

                .antMatchers("/api/user/*").permitAll()
                .antMatchers("/api/role/*").permitAll()

                .and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

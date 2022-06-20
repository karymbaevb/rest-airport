package kg.itacademy.airportmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kg.itacademy.airportmanagement.model.EmployeeApiListResponse;
import kg.itacademy.airportmanagement.model.EmployeeApiModel;
import kg.itacademy.airportmanagement.model.EmployeeApiResponse;
import kg.itacademy.airportmanagement.service.EmployeeApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("employee")
@Slf4j
public class RestTestController {

    @Autowired
    private EmployeeApiService employeeApiService;

    @GetMapping("{id}")
    public ResponseEntity<EmployeeApiResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeApiService.getById(id));
    }

    @GetMapping
    public ResponseEntity<EmployeeApiListResponse> getById() {
        return ResponseEntity.ok(employeeApiService.getAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeApiResponse> create(@RequestBody EmployeeApiModel body) {
        return ResponseEntity.ok(employeeApiService.create(body));
    }


    @GetMapping("old")
    public ResponseEntity<?> sendGet() {
        try {
            // Создаем объект java.net.URL и туда передаем урл
            URL url = new URL("https://dummy.restapiexample.com/api/v1/employees");
            //Открываем подключение для запроса
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Устанавливаем тип запроса, по умолчанию GET
            con.setRequestMethod("GET");
            //Устаналиваем Хедеры, если нужно.
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            //Отправляем запрос и ждем код ответа
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code: " + responseCode);

            //Если код ответа 200, то значит запрос прошел успешно
            if (responseCode == HttpURLConnection.HTTP_OK) {

                //Считываем байт-код из респонса
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                //Сериализуем байт-код в тип String
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response);
                //Закрываем поток подключения
                in.close();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("old")
    public ResponseEntity<?> sendPost() {
        try {
            // Создаем объект java.net.URL и туда передаем урл
            URL url = new URL("https://dummy.restapiexample.com/api/v1/create");
            //Открываем подключение для запроса
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Устанавливаем тип запроса, по умолчанию GET
            con.setRequestMethod("POST");
            //Устаналиваем Хедеры, если нужно.
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            //Выставляем свойство в true, которое означает что мы собираемся отправить запрос с телом
            con.setDoOutput(true);
            //Получаем объект OutputStream из HttpUrlConnection, для того чтобы в дальнейшем отправить тело запроса
            OutputStream os = con.getOutputStream();
            //Здесь устанавливаем тело запроса в формате JSON, и сериализуем в байт код для передачи
            os.write("{\"username\":\"Adam\"}".getBytes(StandardCharsets.UTF_8));
            //Отправляем запрос и ждем код ответа
            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code: " + responseCode);

            //Если код ответа 200, то значит запрос прошел успешно
            if (responseCode == HttpURLConnection.HTTP_OK) {

                //Считываем байт-код из респонса
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                //Сериализуем байт-код в тип String
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println(response);
                //Закрываем поток подключения
                in.close();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

}

package kg.itacademy.airportmanagement.service.impl;

import kg.itacademy.airportmanagement.entity.Airport;
import kg.itacademy.airportmanagement.exceptions.AirportModelNullException;
import kg.itacademy.airportmanagement.exceptions.AirportNotFoundException;
import kg.itacademy.airportmanagement.model.AirportModel;
import kg.itacademy.airportmanagement.repository.AircraftRepository;
import kg.itacademy.airportmanagement.repository.AirportRepository;
import kg.itacademy.airportmanagement.service.AirportService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @Override
    public AirportModel create(AirportModel airportModel) {

        //Валидация
        if (airportModel == null) {
            throw new AirportModelNullException("Create airport model is null");
        } else if (Strings.isBlank(airportModel.getAirportName())) {
            throw new InvalidParameterException("airport name can't be blank");
        }

        //Маппинг
        Airport airport = new Airport();
        airport.setAirportName(airportModel.getAirportName());
        airport.setCity(airportModel.getCity());
        airport.setTimezone(airportModel.getTimezone());
        airport.setCoordinates(airportModel.getCoordinates());
        airport = airportRepository.save(airport);

        // Обратный маппинг
        airportModel.setId(airport.getId());

        //Вернуть результат
        return airportModel;
    }

    @Override
    public boolean update(AirportModel airportModel) {
        //Валидация
        if (airportModel == null) {
            throw new AirportModelNullException("Create airport model is null");
        } else if (airportModel.getAirportName() == null || airportModel.getAirportName().equals("")) {
            throw new InvalidParameterException("airport name can't be empty");
        } else if (airportModel.getId() == null) {
            throw new InvalidParameterException("airport id can't be null");
        }
        //Проверка на то что есть такой аэропорт с таким id
        Airport existAirport = airportRepository.getById(airportModel.getId());
        if (existAirport == null) {
            throw new AirportNotFoundException("airport not found by id " + airportModel.getId());
        }

        //маппим
        existAirport.setAirportName(airportModel.getAirportName());
        existAirport.setCoordinates(airportModel.getCoordinates());
        existAirport.setCity(airportModel.getCity());
        existAirport.setTimezone(airportModel.getTimezone());

        //обновляем
        existAirport = airportRepository.save(existAirport);

        //Если id не нулл после обновляения то можем считать что update прошел успешно в бд.
        return existAirport.getId() != null;
    }

    @Override
    public boolean deleteById(Long id) {
        //Валидация
        if (!airportRepository.existsById(id)) {
            throw new AirportNotFoundException("Airport not found by id: " + id);
        }

        //Удаление
        airportRepository.deleteById(id);

        //Вернеться тру если никаких исключений не произошло.
        return true;
    }

    @Override
    public AirportModel getById(Long id) {
        //Валидация
        if (id == null) {
            throw new InvalidParameterException("Id is null");
        }

        //Ищем в бд с таким айди
        Airport existEntity = airportRepository.getById(id);

        //Если в бд нет такого айди то вернет null. Значит мы должны выбросить исключение о том что не нашли такого аэропорта
        if (existEntity == null) {
            throw new AirportNotFoundException("airport not found by id: " + id);
        }

        //Маппинг
        AirportModel existModel = new AirportModel();
        existModel.setId(existEntity.getId());
        existModel.setAirportName(existEntity.getAirportName());
        existModel.setCoordinates(existEntity.getCoordinates());
        existModel.setTimezone(existEntity.getTimezone());
        existModel.setCity(existEntity.getCity());

        return existModel;
    }

    @Override
    public List<AirportModel> getAllByAirportName(String airportName) {
        //Валидация
        if (Strings.isBlank(airportName)) {
            throw new InvalidParameterException("airport name is blank");
        }

        //Достаем все аэропорты по имени
        List<Airport> airportEntityList = airportRepository.findAllByAirportName(airportName);

        //Создаем пустой массив моделек
        List<AirportModel> airportModelList = new ArrayList<>();

        //Проходимся по каждому чтобы смаппить все элементы в модельки
        for (Airport element : airportEntityList) {
            //Маппим по одному каждый энтити в модель
            AirportModel airportModel = new AirportModel();
            airportModel.setId(element.getId());
            airportModel.setAirportName(element.getAirportName());
            airportModel.setCity(element.getCity());
            airportModel.setCoordinates(element.getCoordinates());
            airportModel.setTimezone(element.getCoordinates());

            //добавляем в массив моделек
            airportModelList.add(airportModel);
        }

        //Возвращаем модельки
        return airportModelList;
    }
}

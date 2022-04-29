package kg.itacademy.airportmanagement.service;

import kg.itacademy.airportmanagement.model.AircraftModel;

public interface AircraftService {
    AircraftModel create(AircraftModel aircraftModel);
    boolean update(AircraftModel aircraftModel);

    AircraftModel getById(Long id);

    boolean deleteById(Long id);
}

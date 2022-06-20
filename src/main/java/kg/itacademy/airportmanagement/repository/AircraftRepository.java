package kg.itacademy.airportmanagement.repository;

import kg.itacademy.airportmanagement.entity.Aircraft;
import kg.itacademy.airportmanagement.entity.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
   @Query(value = "SELECT", nativeQuery = true)
    Aircraft customSelect();
}

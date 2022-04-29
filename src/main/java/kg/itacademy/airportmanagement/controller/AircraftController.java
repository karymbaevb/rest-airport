//package kg.itacademy.airportmanagement.controller;
//
//import kg.itacademy.airportmanagement.model.AircraftModel;
//import kg.itacademy.airportmanagement.model.AirportModel;
//import kg.itacademy.airportmanagement.service.AircraftService;
//import kg.itacademy.airportmanagement.service.AirportService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
////@RestController
////@RequestMapping(path = "/api/aircraft")
////@Slf4j
////@AllArgsConstructor
//public class AircraftController {
//
//    private AircraftService aircraftService;
//
//    @PostMapping(path = "/create")
//    public ResponseEntity<AircraftModel> create(@RequestBody AircraftModel aircraftModel) {
//
//        AircraftModel result = aircraftService.create(aircraftModel);
//        //Если при создании поле id что то есть, значит мы создали успешно и можем вернуть 201
//        if (result.getId() != null) {
//            //вернется со статус 201
//            return ResponseEntity.status(HttpStatus.CREATED).body(result);
//        } else {
//            //вернется со статус 500 если поле id пустое
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    @PutMapping(path = "/update")
//    public ResponseEntity<Boolean> update(@RequestBody AircraftModel aircraftModel) {
//        try {
//            aircraftService.update(aircraftModel);
//            return ResponseEntity.ok(true);
//        } catch (RuntimeException ex) {
//            log.error(ex.getMessage(), ex);
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    @GetMapping(path = "/get/{id}")
//    public ResponseEntity<AircraftModel> getById(@PathVariable("id") Long id) {
//        try {
//            return ResponseEntity.ok(aircraftService.getById(id));
//        } catch (RuntimeException ex) {
//            log.error(ex.getMessage(), ex);
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//
//    @GetMapping(path = "/delete/{id}")
//    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
//        try {
//            return ResponseEntity.ok(aircraftService.deleteById(id));
//        } catch (RuntimeException ex) {
//            log.error(ex.getMessage(), ex);
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
//        }
//    }
//}
//

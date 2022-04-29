package kg.itacademy.airportmanagement.handler;


import kg.itacademy.airportmanagement.exceptions.ImDontKnowException;
import kg.itacademy.airportmanagement.model.ErrorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class RestApiExceptionHandler extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ImDontKnowException.class)
    public ResponseEntity<ErrorModel> handleValidationExceptions(ImDontKnowException ex) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setMsg(ex.getMessage());
        errorModel.setExceptionClassName(ImDontKnowException.class.toString());
        return ResponseEntity.status(ex.getStatus()).body(errorModel);
    }
}

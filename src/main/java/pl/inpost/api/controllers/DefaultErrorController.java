package pl.inpost.api.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.inpost.api.dto.model.ErrorDTO;

@Hidden
@ControllerAdvice
public class DefaultErrorController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDTO> handleNotFoundException(Exception exception) {
        return getCommonResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErrorDTO> getCommonResponseEntity(Exception exception,
                                                           HttpStatus httpStatus) {

        ErrorDTO errorDetails = new ErrorDTO()
                .code(httpStatus.value())
                .message(exception.getLocalizedMessage());
        return new ResponseEntity<>(errorDetails, httpStatus);
    }

}

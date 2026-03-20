package br.com.futsal.futsaldrawsystemapi.exception;

import br.com.futsal.futsaldrawsystemapi.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO apiException(ApiException ex) {
        return ResponseErrorDTO.builder()
                .error(ex.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseErrorDTO notFoundException(NotFoundException ex) {
        return ResponseErrorDTO.builder()
                .error(ex.getMessage())
                .build();
    }

}
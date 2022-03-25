package br.com.goodpratices.resources.handlers;

import br.com.goodpratices.services.dto.ExceptionResponse;
import br.com.goodpratices.resources.handlers.enums.ExceptionsMessagesEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Log4j2
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new ExceptionResponse(ExceptionsMessagesEnum.INTERNAL_ERROR.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> handleNotFound(RuntimeException ex) {
        log.info(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

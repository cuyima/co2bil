package de.vlaasch.co2bil.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.vlaasch.co2bil.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<String> handleBadRequest(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUnexpectedError(Exception e) {
        log.error("An unhandled error occurred.", e);
        return ResponseEntity.internalServerError().body("An unexpected error occurred, please try again later.");
    }

}

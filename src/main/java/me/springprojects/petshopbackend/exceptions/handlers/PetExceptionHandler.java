package me.springprojects.petshopbackend.exceptions.handlers;

import me.springprojects.petshopbackend.exceptions.InvalidPetException;
import me.springprojects.petshopbackend.exceptions.dto.InvalidPetExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class PetExceptionHandler {

    @ExceptionHandler(value = InvalidPetException.class)
    public ResponseEntity<InvalidPetExceptionDTO> handleInvalidPetException(InvalidPetException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        InvalidPetExceptionDTO res = new InvalidPetExceptionDTO(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now()
        );
        return ResponseEntity.status(httpStatus).body(res);
    }
}

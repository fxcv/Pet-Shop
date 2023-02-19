package me.springprojects.petshopbackend.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class InvalidPetExceptionDTO {

    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime dateTime;
}
